import java.io.*;
import java.net.*;
import java.util.Date;

public class Server_DT {
    public static void main(String[] args) throws IOException {

        ServerSocket ss = new ServerSocket(5000);

        System.out.println("Server started on port: " + ss.getLocalPort());
        System.out.println("Waiting for client...");

        while (true) {
            Socket cs = ss.accept();nj   

            System.out.println("Client connected: " + cs.getInetAddress());
            System.out.println("Client port: " + cs.getPort());

            BufferedReader fromClient =
                    new BufferedReader(
                            new InputStreamReader(cs.getInputStream()));

            String clientIP = fromClient.readLine();

            System.out.println("Client IP Address: " + clientIP);

            Date d = new Date();
            String s = "Current Date & Time on Server: " + d;

            PrintWriter toClient =
                    new PrintWriter(cs.getOutputStream(), true);

            toClient.println(s);

            fromClient.close();
            toClient.close();
            cs.close();
        }
    }
}