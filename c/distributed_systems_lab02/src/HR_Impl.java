import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

//Remote Object
public class HR_Impl extends UnicastRemoteObject implements HR_Interface {

    private final List<Person> personArrayList = new ArrayList<>();
    private final List<RoomListener> roomListenerA = new ArrayList<>();
    private final List<RoomListener> roomListenerB = new ArrayList<>();
    private final List<RoomListener> roomListenerC = new ArrayList<>();
    private final List<RoomListener> roomListenerD = new ArrayList<>();
    private final List<RoomListener> roomListenerE = new ArrayList<>();

    Hotel mHotel = new Hotel();

    protected HR_Impl() throws RemoteException {
    }

    @Override
    public String list() throws RemoteException {
        String listRooms = "";
        for (int i = 0; i < 5; i++) {
            listRooms += mHotel.availability[i] + " rooms type " + mHotel.pos[i] + " " + mHotel.price[i] + " euros/night\n";
        }
        return listRooms;
    }

    @Override
    public String book(String[] args) throws RemoteException {

        List<String> checkRoomType = new ArrayList<>(Arrays.asList(mHotel.pos));

        String bookResult = "";
        int roomsCount = Integer.parseInt(args[3]);
        int roomsBill = 0;

        if (checkRoomType.contains(args[2])) {
            int index = checkRoomType.indexOf(args[2]);
            if (roomsCount <= mHotel.availability[index]) {
                mHotel.availability[index] -= roomsCount;
                roomsBill = roomsCount * mHotel.price[index];
                bookResult = roomsBill + " Euros is your total bill, " + args[4] + ".";
                Person mPerson = new Person(roomsCount, args[2], args[4], roomsBill);
                personArrayList.add(mPerson);
            } else if (roomsCount > mHotel.availability[index] && mHotel.availability[index] > 0) {
                bookResult = "Booking Unsuccessful. Rooms are not enough.\n Do you want to book " + mHotel.availability[index] + " rooms instead?";
            } else
                bookResult = "Booking Unsuccessful. Rooms not available.\n Do you want to subscribe to a list?.";
        }
        return bookResult;
    }

    @Override
    public String guests() throws RemoteException {
        String guestResult = "";
        for (Person guest : personArrayList) {
            guestResult = guest.name + " has booked " + guest.roomsCount + " rooms of type "
                    + guest.roomType + " for " + guest.roomBill + " euros.";
        }
        if (guestResult.equals(""))
            return "Guests not found!";

        return guestResult;
    }

    @Override
    public String cancel(String[] args) throws RemoteException {
        int roomsCount = Integer.parseInt(args[3]);
        int roomsBill = 0;

        for (Person person : personArrayList) {
            if (person.name.equals(args[4]) && person.roomType.equals(args[2])) {
                List<String> checkRoomType = new ArrayList<>(Arrays.asList(mHotel.pos));
                int position = personArrayList.indexOf(person);
                System.out.println(position);
                int index = checkRoomType.indexOf(args[2]);
                if (person.roomsCount - roomsCount > 0) {
                    roomsBill = roomsCount * mHotel.price[index];
                    Person newPerson = new Person(person.roomsCount - roomsCount, args[2], args[4], roomsBill);
                    personArrayList.set(position, newPerson);
                    mHotel.availability[index] += roomsCount;
                    notifyRoomListeners(args[2]);
                    return "Updated booking:  " + newPerson.name + " has booked " + newPerson.roomsCount + " rooms of type "
                            + newPerson.roomType + " for " + newPerson.roomBill + " euros.";
                } else {
                    personArrayList.remove(position);
                    notifyRoomListeners(args[2]);
                    return "Removed booking!";
                }
            }
        }
        return "Cant find specified room!";
    }

    private void notifyRoomListeners(String roomType) {
        switch (roomType) {
            case "A":
                for (RoomListener rListener : roomListenerA)
                    try {
                        rListener.roomCanceled("Rooms of type A are now free!");
                    } catch (RemoteException aInE) {
                        roomListenerA.remove(rListener);
                    }
                break;
            case "B":
                for (RoomListener rListener : roomListenerA)
                    try {
                        rListener.roomCanceled("Rooms of type B are now free!");
                    } catch (RemoteException aInE) {
                        roomListenerB.remove(rListener);
                    }
                break;
            case "C":
                for (RoomListener rListener : roomListenerA)
                    try {
                        rListener.roomCanceled("Rooms of type C are now free!");
                    } catch (RemoteException aInE) {
                        roomListenerC.remove(rListener);
                    }
                break;
            case "D":
                for (RoomListener rListener : roomListenerA)
                    try {
                        rListener.roomCanceled("Rooms of type D are now free!");
                    } catch (RemoteException aInE) {
                        roomListenerD.remove(rListener);
                    }
                break;
            case "E":
                for (RoomListener rListener : roomListenerA)
                    try {
                        rListener.roomCanceled("Rooms of type E are now free!");
                    } catch (RemoteException aInE) {
                        roomListenerE.remove(rListener);
                    }
                break;
        }
    }

    public String addRoomListener(String roomType, RoomListener roomListener) {
        switch (roomType) {
            case "A" -> roomListenerA.add(roomListener);
            case "B" -> roomListenerB.add(roomListener);
            case "C" -> roomListenerC.add(roomListener);
            case "D" -> roomListenerD.add(roomListener);
            case "E" -> roomListenerE.add(roomListener);
        }
        return "Added roomListener!";
    }

    public String removeRoomListener(String roomType, RoomListener roomListener) {
        switch (roomType) {
            case "A" -> roomListenerA.remove(roomListener);
            case "B" -> roomListenerB.remove(roomListener);
            case "C" -> roomListenerC.remove(roomListener);
            case "D" -> roomListenerD.remove(roomListener);
            case "E" -> roomListenerE.remove(roomListener);
        }
        return "Removed roomListener!";
    }

    public static void main(String[] args) {
        try {
            HR_Impl lServer = new HR_Impl();
            Registry reg = LocateRegistry.createRegistry(52369);
            String url = "rmi://" + InetAddress.getLocalHost().getHostAddress() + ":52369/Hello";
            Naming.rebind(url, lServer);

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
