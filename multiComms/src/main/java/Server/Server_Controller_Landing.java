package Server;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Server_Controller_Landing {
    @FXML
    public void changeScene(ActionEvent actionEvent, String newScene) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(String.valueOf(newScene)));
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void goToFiles(ActionEvent actionEvent) throws IOException {
        Server_Global_Data.getFilesFromDir(System.getProperty("user.dir") + "/FFmpeg_videos/built_videos");
        changeScene(actionEvent, "Server_Files_Scene.fxml");
    }
}
