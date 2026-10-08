import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);

            System.out.println("Server started...");
            System.out.println("Waiting for client...");

            Socket socket = serverSocket.accept();

            System.out.println("Client connected!");

            socket.setKeepAlive(true);

            socket.setSoLinger(true, 10);

            socket.setSendBufferSize(8192);

            socket.setReceiveBufferSize(8192);

            socket.setTcpNoDelay(true);

            System.out.println("\n--- Socket Options ---");

            System.out.println("SO_KEEPALIVE: "
                    + socket.getKeepAlive());

            System.out.println("SO_LINGER: "
                    + socket.getSoLinger());

            System.out.println("SO_SNDBUF: "
                    + socket.getSendBufferSize());

            System.out.println("SO_RCVBUF: "
                    + socket.getReceiveBufferSize());

            System.out.println("TCP_NODELAY: "
                    + socket.getTcpNoDelay());

            InetAddress address = socket.getInetAddress();

            System.out.println("\n--- Client Information ---");

            System.out.println("Client IP: "
                    + address.getHostAddress());

            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(
                                    socket.getInputStream()));

            PrintWriter writer =
                    new PrintWriter(
                            socket.getOutputStream(), true);

            String message = reader.readLine();

            System.out.println("Client: " + message);

            writer.println("Message received by server.");

            reader.close();
            writer.close();
            socket.close();
            serverSocket.close();

            System.out.println("Server closed.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}