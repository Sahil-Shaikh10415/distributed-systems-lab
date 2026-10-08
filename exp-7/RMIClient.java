import java.rmi.Naming;

public class RMIClient {
    public static void main(String[] args) {
        try {
            RemoteInterface obj =
                (RemoteInterface) Naming.lookup(
                    "rmi://localhost/Calculator"
                );

            int result = obj.add(10, 20);

            System.out.println("Result = " + result);
        } catch (Exception e) {
            System.out.println("Client Error: " + e);
        }
    }
}