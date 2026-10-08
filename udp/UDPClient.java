import java.net.*;
import java.util.Scanner;

public class UDPClient {

    public static void main(String[] args) {

        try {
            DatagramSocket socket =
                    new DatagramSocket();

            Scanner sc = new Scanner(System.in);

            System.out.print("Enter message: ");
            String message = sc.nextLine();

            byte[] data = message.getBytes();

            InetAddress address =
                    InetAddress.getByName("localhost");

            DatagramPacket request =
                    new DatagramPacket(
                            data,
                            data.length,
                            address,
                            6789
                    );

            socket.send(request);

            byte[] buffer = new byte[1000];

            DatagramPacket reply =
                    new DatagramPacket(
                            buffer,
                            buffer.length
                    );

            socket.receive(reply);

            String response =
                    new String(
                            reply.getData(),
                            0,
                            reply.getLength()
                    );

            System.out.println(
                    "Server replied: " + response
            );

            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}