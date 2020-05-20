import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

//Remote Object
public class HR_Impl extends UnicastRemoteObject implements HR_Interface {

    //ADD CLIENTS???
    private final List<HR_Impl> listeners = new ArrayList<>();
    //Declaring Variables
    private volatile int A_Price;
    private volatile int B_Price;
    private volatile int C_Price;
    private volatile int D_Price;
    private volatile int E_Price;

    private volatile int A_Availability;
    private volatile int B_Availability;
    private volatile int C_Availability;
    private volatile int D_Availability;
    private volatile int E_Availability;


    protected HR_Impl() throws RemoteException {
        //DEFAULTS
        //Price of each room type
        A_Price = 50;
        B_Price = 70;
        C_Price = 80;
        D_Price = 120;
        E_Price = 150;

        //Availability of each room type
        A_Availability = 30;
        B_Availability = 45;
        C_Availability = 25;
        D_Availability = 10;
        E_Availability = 5;
    }


    @Override
    public String list() throws RemoteException {
        String listRooms;
        listRooms = A_Availability + "rooms type A" + A_Price + "euros/night";
        return "hey";
    }

    @Override
    public void book() throws RemoteException {

    }

    @Override
    public void guests() throws RemoteException {

    }

    @Override
    public void cancel() throws RemoteException {

    }

    public static void main(String[] args) {
        try {
            // Create the Remote Object
            HR_Impl lServer = new HR_Impl();
            // Binding the remote object (stub) in our own registry
            Registry reg = LocateRegistry.createRegistry(52369);
            String url = "rmi://" + InetAddress.getLocalHost().getHostAddress() + ":52369/Hello";

            Naming.rebind(url, lServer);

            //Create the thread and change the temperature
            Thread lThread = new Thread((Runnable) lServer);
            lThread.start();

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
