import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HR_Server extends Remote {


    String list() throws RemoteException;

//
//    public void book() {
//    }
//
//    public void guests() {
//    }
//
//    public void cancel() {
//    }

}
