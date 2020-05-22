public class Hotel {

    String[] pos = new String[5];
    int[] price = new int[5];
    int[] availability = new int[5];

    protected Hotel() {
        //DEFAULTS

        //Availability of each room type
        pos[0] = "A";
        pos[1] = "B";
        pos[2] = "C";
        pos[3] = "D";
        pos[4] = "E";

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
}
