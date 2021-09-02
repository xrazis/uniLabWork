import java.io.Serializable;

public class Person implements Serializable {
    String name;

    int roomsCount;
    int roomBill;
    String roomType;

    public Person(int roomsCount, String roomType, String name, int roomBill) {
        this.roomsCount = roomsCount;
        this.roomType = roomType;
        this.name = name;
        this.roomBill = roomBill;
    }

}
