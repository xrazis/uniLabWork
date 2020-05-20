import java.rmi.Remote;
import java.rmi.RemoteException;

//RMI
public interface HR_Interface extends Remote {
    String list() throws RemoteException;

    void book() throws RemoteException;

    void guests() throws RemoteException;

    void cancel() throws RemoteException;

}
