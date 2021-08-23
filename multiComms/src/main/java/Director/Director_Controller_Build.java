package Director;

import Server.Server_Global_Data;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Director_Controller_Build implements Initializable {

    public ListView<String> filesToBuild;

    @FXML
    public void changeScene(ActionEvent actionEvent, String newScene) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(String.valueOf(newScene)));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void buildFiles(ActionEvent actionEvent) throws IOException {
        Director_Global_Data.buildFiles();
        changeScene(actionEvent, "Director_Finish_Scene.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        filesToBuild.setItems(FXCollections.observableArrayList(Director_Global_Data.videos));
    }
}
