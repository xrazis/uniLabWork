import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RoomListener extends Remote {
    void roomCanceled(String notify) throws RemoteException;
}
