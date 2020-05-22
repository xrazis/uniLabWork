import java.rmi.Remote;
import java.rmi.RemoteException;

//RMI
public interface HR_Interface extends Remote {
    String list() throws RemoteException;

    String book(String[] args) throws RemoteException;

    String guests() throws RemoteException;

    String cancel(String[] args) throws RemoteException;

    String addRoomListener(String roomType, RoomListener addRoomListener) throws RemoteException;

    String removeRoomListener(String roomType, RoomListener removeRoomListener) throws RemoteException;

}
