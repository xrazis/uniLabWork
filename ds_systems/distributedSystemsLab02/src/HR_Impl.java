import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

//Remote Object
public class HR_Impl extends UnicastRemoteObject implements HR_Interface {

    private final List<Person> personArrayList = new CopyOnWriteArrayList<>();
    private final List<RoomListener> roomListenerA = new ArrayList<>();
    private final List<RoomListener> roomListenerB = new ArrayList<>();
    private final List<RoomListener> roomListenerC = new ArrayList<>();
    private final List<RoomListener> roomListenerD = new ArrayList<>();
    private final List<RoomListener> roomListenerE = new ArrayList<>();

    Hotel mHotel = new Hotel();

    protected HR_Impl() throws RemoteException {
    }

    @Override
    public StringBuilder list() throws RemoteException {
        StringBuilder listRooms = new StringBuilder();
        listRooms.append(mHotel.availability[0]).append(" rooms type A").append("A").append(" ").append(mHotel.price[0]).append(" euros/night\n");
        listRooms.append(mHotel.availability[1]).append(" rooms type B").append("B").append(" ").append(mHotel.price[1]).append(" euros/night\n");
        listRooms.append(mHotel.availability[2]).append(" rooms type C").append("C").append(" ").append(mHotel.price[2]).append(" euros/night\n");
        listRooms.append(mHotel.availability[3]).append(" rooms type D").append("D").append(" ").append(mHotel.price[3]).append(" euros/night\n");
        listRooms.append(mHotel.availability[4]).append(" rooms type E").append("E").append(" ").append(mHotel.price[4]).append(" euros/night\n");

        return listRooms;
    }

    @Override
    public String book(String[] args) throws RemoteException {
        String roomType = args[2];
        int roomCount = Integer.parseInt(args[3]);
        String roomName = args[4];

        int roomBill = 0;
        String bookResult = "SERVER-SIDE ERROR";

        if (mHotel.book(roomType, roomCount)) {
            roomBill = roomCount * mHotel.getPrice(roomType);
            bookResult = roomBill + " Euros is your total bill, " + roomName + ".";
            Person mPerson = new Person(roomCount, roomType, roomName, roomBill);
            personArrayList.add(mPerson);
        } else {
            int roomAvailability = mHotel.getAvail(roomType);
            if (roomAvailability > 0) {
                bookResult = "Booking Unsuccessful. Rooms are not enough.\n Do you want to book " + roomAvailability + " rooms instead?";
            } else {
                bookResult = "Booking Unsuccessful. Rooms not available.\n Do you want to subscribe to a list?.";
            }
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
        String roomType = args[2];
        int roomCount = Integer.parseInt(args[3]);
        String roomName = args[4];

        int roomBill = 0;
        String cancelResult = "SERVER-SIDE ERROR";

        for (Person person : personArrayList) {
            if (mHotel.cancel(person, roomType, roomName)) {
                if (person.roomsCount - roomCount > 0) {
                    roomBill = roomCount * mHotel.getPrice(roomType);
                    Person newPerson = new Person(person.roomsCount - roomCount, roomType, roomName, roomBill);
                    personArrayList.set(personArrayList.indexOf(person), newPerson);
                    mHotel.updateAvail(roomType, roomCount);
                    notifyRoomListeners(roomType);
                    cancelResult = "Updated booking:  " + newPerson.name + " has booked " + newPerson.roomsCount + " rooms of type "
                            + newPerson.roomType + " for " + newPerson.roomBill + " euros.";
                } else {
                    personArrayList.remove(person);
                    mHotel.updateAvail(roomType, roomCount);
                    notifyRoomListeners(roomType);
                    cancelResult = "Removed booking!";
                }
            }
        }
        return cancelResult;
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
