package Director;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Director extends Application {
    public static Director_Global_Data mData = new Director_Global_Data();

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Director_Landing_Scene.fxml"));
        stage.setTitle("Multimedia Communications");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        Director_Global_Data.getFilesFromDir("/home/xaris/Desktop/repos/Uni/MultiComms_lab/FFmpeg_videos/raw_videos");
        launch(args);
    }
}