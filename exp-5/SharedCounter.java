import java.util.concurrent.atomic.AtomicInteger;

public class SharedCounter {
    static AtomicInteger counter = new AtomicInteger(0);

    static class IncrementTask implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                counter.incrementAndGet();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new IncrementTask());
        Thread t2 = new Thread(new IncrementTask());
        Thread t3 = new Thread(new IncrementTask());
        Thread t4 = new Thread(new IncrementTask());

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();

        System.out.println("Final counter = " + counter.get());
    }
}