import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000);

            System.out.println("Connected to server.");

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

            System.out.println("\nServer IP: "
                    + address.getHostAddress());

            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(
                                    socket.getInputStream()));

            PrintWriter writer =
                    new PrintWriter(
                            socket.getOutputStream(), true);

            writer.println("Hello Server!");

            String response = reader.readLine();

            System.out.println("Server: " + response);

            reader.close();
            writer.close();
            socket.close();

            System.out.println("Client closed.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}