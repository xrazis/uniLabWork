package Client;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Client_Controller_Options02 implements Initializable {

    public ChoiceBox<String> protocolToTransfer;
    public ChoiceBox<String> fileToTransfer;

    public static String file;
    public static String protocol;

    Runnable operations = () -> {
        try {
            Client_Global_Data.getFile(file, protocol);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    };

    public void exit(ActionEvent actionEvent) {
        //close the thread
        Stage stage = (Stage) protocolToTransfer.getScene().getWindow();
        stage.close();
    }

    public void getFile(ActionEvent actionEvent) {
        file = fileToTransfer.getValue();
        protocol = protocolToTransfer.getValue();
        new Thread(operations).start();
//        exit(actionEvent);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileToTransfer.setItems(FXCollections.observableArrayList(Client_Global_Data.matchingVideos));
    }
}
