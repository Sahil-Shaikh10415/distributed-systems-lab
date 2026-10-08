import java.io.*;
import java.net.*;

public class SimpleServer {
    public static void main(String[] args) throws IOException {

        // Create server and listen on port 1234
        ServerSocket server = new ServerSocket(1234);

        System.out.println("Server started...");
        System.out.println("Waiting for client...");

        // Wait for client
        Socket client = server.accept();

        System.out.println("Client connected!");

        // Create output stream
        DataOutputStream dos =
                new DataOutputStream(client.getOutputStream());

        // Send message to client
        dos.writeUTF("Hi there");

        System.out.println("Message sent!");

        // Close connection
        dos.close();
        client.close();
        server.close();
    }
}
