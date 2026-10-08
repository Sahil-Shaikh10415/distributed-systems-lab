import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class RemoteImplementation
        extends UnicastRemoteObject
        implements RemoteInterface {

    public RemoteImplementation() throws RemoteException {
        super();
    }

    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }
}