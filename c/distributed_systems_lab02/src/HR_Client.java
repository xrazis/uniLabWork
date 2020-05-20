import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.Remote;

public class HR_Client {
    public static void main(String[] args) {
        String error = "Wrong number of arguments!";
        try {
            //Lookup for the service to our own registry
            String url = "rmi://" + InetAddress.getLocalHost().getHostAddress() + ":52369/Hello";
            Remote remoteObject = Naming.lookup(url);
            HR_Server remoteServer = (HR_Server) remoteObject;

            System.out.println("Welcome to the (somewhat) Booking clone");
            if (args.length == 0) {
                System.out.println("~Menu~\n" +
                        "1. Display all available rooms: HR_Client list <hostname>\n" +
                        "2. Book x rooms of y type in name z: HR_Client book <hostname> <x> <y> <z>\n" +
                        "3. List all clients: HR_Client guests <hostname>\n" +
                        "4. Cancel x rooms of y type in name z: HR_Client cancel\n");
            } else {
                switch (args[0]) {
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
                            remoteServer.book();
                        } else {
                            System.err.println(error);
                            System.exit(1);
                        }
                        break;
                    case "guests":
                        if (args.length == 2) {
                            remoteServer.guests();
                        } else {
                            System.err.println(error);
                            System.exit(1);
                        }
                        break;
                    case "cancel":
                        if (args.length == 5) {
                            remoteServer.cancel();
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
}
