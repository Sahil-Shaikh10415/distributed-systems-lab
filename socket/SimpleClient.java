import java.io.*;
import java.net.*;

public class SimpleClient {
    public static void main(String[] args) throws IOException {

        // Connect to server at localhost, port 1234
        Socket client = new Socket("localhost", 1234);

        System.out.println("Connected to server!");

        // Create input stream
        DataInputStream dis =
                new DataInputStream(client.getInputStream());

        // Receive message
        String message = dis.readUTF();

        // Display message
        System.out.println("Server says: " + message);

        // Close connection
        dis.close();
        client.close();
    }
}