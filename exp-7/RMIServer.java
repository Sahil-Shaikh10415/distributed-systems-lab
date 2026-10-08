import java.rmi.Naming;

public class RMIServer {
    public static void main(String[] args) {
        try {
            RemoteImplementation obj = new RemoteImplementation();

            Naming.rebind("rmi://localhost/Calculator", obj);

            System.out.println("RMI Server is running...");
        } catch (Exception e) {
            System.out.println("Server Error: " + e);
        }
    }
}