package ClientSide;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static ClientSide.Client_Data.*;

public class Client extends Application {

    public Client_Data mData = new Client_Data();

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println(mData.makeServerConn("localhost", 5000));
        Parent root = FXMLLoader.load(getClass().getResource("LandingScene.fxml"));
        primaryStage.setTitle("Multimedia Communications");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }
}
