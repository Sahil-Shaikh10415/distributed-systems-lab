import java.net.*;

public class UDPServer {

    public static void main(String[] args) {

        try {
            DatagramSocket socket =
                    new DatagramSocket(6789);

            byte[] buffer = new byte[1000];

            System.out.println("UDP Server started...");
            System.out.println("Waiting for message...");

            while (true) {

                DatagramPacket request =
                        new DatagramPacket(
                                buffer,
                                buffer.length
                        );

                socket.receive(request);

                String message =
                        new String(
                                request.getData(),
                                0,
                                request.getLength()
                        );

                System.out.println(
                        "Client: " + message
                );

                DatagramPacket reply =
                        new DatagramPacket(
                                request.getData(),
                                request.getLength(),
                                request.getAddress(),
                                request.getPort()
                        );

                socket.send(reply);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}