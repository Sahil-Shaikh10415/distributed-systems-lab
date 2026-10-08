import java.io.*;
import java.net.*;

public class Client_DT {
    public static void main(String[] args)
            throws UnknownHostException, IOException {

        Socket cs = new Socket("localhost", 5000);

        System.out.println(
                "Client " + cs.getLocalAddress()
                + " is communicating from port: "
                + cs.getLocalPort());

        InetAddress address = InetAddress.getLocalHost();
        String clientIP = address.getHostAddress();

        PrintWriter toServer =
                new PrintWriter(cs.getOutputStream(), true);

        toServer.println(clientIP);

        BufferedReader fromServer =
                new BufferedReader(
                        new InputStreamReader(cs.getInputStream()));

        System.out.println(fromServer.readLine());

        toServer.close();
        fromServer.close();
        cs.close();
    }
}