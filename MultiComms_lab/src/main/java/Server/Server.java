package Server;

import Director.Director_Global_Data;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Server extends Application {
    public static Server_Global_Data mData = new Server_Global_Data();

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Server_Landing_Scene.fxml"));
        stage.setTitle("Multimedia Communications");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }
}
