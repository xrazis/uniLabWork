package Client;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Client_Controller_Options02 implements Initializable {

    public ChoiceBox protocolToTransfer;
    public ChoiceBox fileToTransfer;

    @FXML
    public void changeScene(ActionEvent actionEvent, String newScene) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(String.valueOf(newScene)));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void getFile(ActionEvent actionEvent) throws IOException {
        String file = (String) fileToTransfer.getValue();
        String protocol = (String) protocolToTransfer.getValue();
        Client_Global_Data.getFile(file, protocol);
//        changeScene(actionEvent,"");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileToTransfer.setItems(FXCollections.observableArrayList(Client_Global_Data.matchingVideos));
    }
}
