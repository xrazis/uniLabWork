package Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Client_Controller_Options01 {
    public ChoiceBox speedChoiceBox;
    public ChoiceBox formatChoiceBox;

    @FXML
    public void changeScene(ActionEvent actionEvent, String newScene) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(String.valueOf(newScene)));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void getSpeedFormat(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        String speed = (String) speedChoiceBox.getValue();
        String format = (String) formatChoiceBox.getValue();
        Client_Global_Data.sendSpeedFormat(speed, format);
        changeScene(actionEvent, "Client_Options_Scene02.fxml");
    }
}