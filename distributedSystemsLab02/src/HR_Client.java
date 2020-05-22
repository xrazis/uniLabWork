import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class HR_Client extends UnicastRemoteObject implements RoomListener {
    protected HR_Client() throws RemoteException {
    }

    public static void main(String[] args) {
        String error = "Wrong number of arguments!";
        try {
            String url = "rmi://" + InetAddress.getLocalHost().getHostAddress() + ":52369/Hello";
            Remote remoteObject = Naming.lookup(url);
            HR_Interface remoteServer = (HR_Interface) remoteObject;

            System.out.println("Welcome to the (somewhat) Booking clone");
            if (args.length == 1) {
                System.out.println("~Menu~\n" +
                        "1. Display all available rooms: HR_Client list <hostname>\n" +
                        "2. Book x rooms of y type in name z: HR_Client book <hostname> <x> <y> <z>\n" +
                        "3. List all clients: HR_Client guests <hostname>\n" +
                        "4. Cancel x rooms of y type in name z: HR_Client cancel\n");
            } else {
                switch (args[1]) {
                    case "list":
                        if (args.length == 2) {
                            System.out.println(remoteServer.list());
                        } else {
                            System.err.println(error);
                            System.exit(1);
                        }
                        break;
                    case "book":
                        if (args.length == 5) {
                            String response = remoteServer.book(args);
                            System.out.println(response);
                            if (response.contains("list")) {
                                Scanner myObj = new Scanner(System.in);
                                String listAns = myObj.nextLine();
                                if (listAns.equals("Y") || listAns.equals("y") || listAns.equals("YES") || listAns.equals("yes")) {
                                    HR_Client mRoomListener = new HR_Client();
                                    System.out.println(remoteServer.addRoomListener(args[2], mRoomListener));
                                }
                            }
                        } else {
                            System.err.println(error);
                            System.exit(1);
                        }
                        break;
                    case "guests":
                        if (args.length == 2) {
                            System.out.println(remoteServer.guests());
                        } else {
                            System.err.println(error);
                            System.exit(1);
                        }
                        break;
                    case "cancel":
                        if (args.length == 5) {
                            System.out.println(remoteServer.cancel(args));
                        } else {
                            System.err.println(error);
                            System.exit(1);
                        }
                        break;
                }
            }
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public void roomCanceled(String notify) throws RemoteException {
        // REMOVE LISTENER AFTER NOTIFY
        System.out.println(notify);
    }
}
