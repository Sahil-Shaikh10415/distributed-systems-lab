import java.util.*;

class Process implements Runnable {

    private int id;
    private int timestamp = 0;
    private boolean requesting = false;

    private Set<Integer> replies = new HashSet<>();
    private List<Integer> deferredReplies = new ArrayList<>();

    private static List<Process> processes = new ArrayList<>();

    Process(int id) {
        this.id = id;
    }

    public static void addProcess(Process p) {
        processes.add(p);
    }

    // Request entry into critical section
    public synchronized void requestCriticalSection() {

        timestamp++;
        requesting = true;
        replies.clear();

        System.out.println("Process " + id +
                " requests Critical Section at timestamp " + timestamp);

        // Send REQUEST to all other processes
        for (Process p : processes) {
            if (p.id != this.id) {
                p.receiveRequest(this.id, timestamp);
            }
        }

        // Wait until all processes reply
        while (replies.size() < processes.size() - 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        enterCriticalSection();
    }

    // Receive REQUEST from another process
    public synchronized void receiveRequest(int senderId, int senderTimestamp) {

        timestamp = Math.max(timestamp, senderTimestamp) + 1;

        boolean sendReply;

        if (!requesting) {
            sendReply = true;
        } else {
            // Compare timestamps
            if (senderTimestamp < timestamp ||
                (senderTimestamp == timestamp && senderId < id)) {
                sendReply = true;
            } else {
                sendReply = false;
            }
        }

        if (sendReply) {
            sendReply(senderId);
        } else {
            deferredReplies.add(senderId);
        }
    }

    // Send REPLY
    private void sendReply(int receiverId) {

        for (Process p : processes) {
            if (p.id == receiverId) {
                p.receiveReply(id);
                break;
            }
        }
    }

    // Receive REPLY
    public synchronized void receiveReply(int senderId) {

        replies.add(senderId);

        if (replies.size() == processes.size() - 1) {
            notify();
        }
    }

    // Critical Section
    private void enterCriticalSection() {

        System.out.println("Process " + id +
                " ENTERED Critical Section");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Process " + id +
                " EXITED Critical Section");

        requesting = false;

        // Send deferred replies
        for (int processId : deferredReplies) {
            sendReply(processId);
        }

        deferredReplies.clear();
    }

    @Override
    public void run() {

        try {
            Thread.sleep(id * 500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        requestCriticalSection();
    }
}

public class DistributedMutualExclusion {

    public static void main(String[] args) throws InterruptedException {

        int numberOfProcesses = 4;

        for (int i = 1; i <= numberOfProcesses; i++) {
            Process p = new Process(i);
            Process.addProcess(p);
        }

        List<Thread> threads = new ArrayList<>();

        for (Process p : getProcesses()) {
            Thread t = new Thread(p);
            threads.add(t);
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("\nAll processes completed.");
    }

    // Helper method to access processes
    private static List<Process> getProcesses() {
        try {
            var field = Process.class.getDeclaredField("processes");
            field.setAccessible(true);
            return (List<Process>) field.get(null);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}