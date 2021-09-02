import java.util.ArrayList;

public class Hotel {

    int[] price = new int[5];
    int[] availability = new int[5];

    protected Hotel() {
        //DEFAULTS

        //Price of each room type
        price[0] = 50;
        price[1] = 70;
        price[2] = 80;
        price[3] = 120;
        price[4] = 150;

        //Availability of each room type
        availability[0] = 30;
        availability[1] = 45;
        availability[2] = 25;
        availability[3] = 10;
        availability[4] = 5;
    }

    public int getPrice(String roomType) {
        return switch (roomType) {
            case "A" -> this.price[0];
            case "B" -> this.price[1];
            case "C" -> this.price[2];
            case "D" -> this.price[3];
            case "E" -> this.price[4];
            default -> 0;
        };
    }

    public int getAvail(String roomType) {
        return switch (roomType) {
            case "A" -> this.availability[0];
            case "B" -> this.availability[1];
            case "C" -> this.availability[2];
            case "D" -> this.availability[3];
            case "E" -> this.availability[4];
            default -> 0;
        };
    }

    public void updateAvail(String roomType, int roomCount) {
        switch (roomType) {
            case "A" -> this.availability[0] += roomCount;
            case "B" -> this.availability[1] += roomCount;
            case "C" -> this.availability[2] += roomCount;
            case "D" -> this.availability[3] += roomCount;
            case "E" -> this.availability[4] += roomCount;
            default -> throw new IllegalStateException("Unexpected value: " + roomType);
        }
    }

    protected boolean book(String roomType, int roomCount) {
        return switch (roomType) {
            case "A" -> this.availability[0] > roomCount;
            case "B" -> this.availability[1] > roomCount;
            case "C" -> this.availability[2] > roomCount;
            case "D" -> this.availability[3] > roomCount;
            case "E" -> this.availability[4] > roomCount;
            default -> false;
        };
    }

    public boolean cancel(Person person, String roomType, String roomName) {
        return person.name.equals(roomName) && person.roomType.equals(roomType);
    }


}
