package ClientSide;

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

public class Controller_Options01 implements Initializable {
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

    public void getSpeedFormat(ActionEvent actionEvent) throws IOException {
        String speed = (String) speedChoiceBox.getValue();
        String format = (String) formatChoiceBox.getValue();
//        System.out.println(Client_Data.sendSpeedFormat(speed, format));
        //        changeScene(actionEvent, "OptionScene02.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Client_Data.test();
    }
}
