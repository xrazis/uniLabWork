package ClientSide;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.Socket;

public class Controller {
    public void connectToServer(ActionEvent actionEvent) {
        try {
            Socket socket = new Socket("localhost", 5000);
            System.out.println("Connected");

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
