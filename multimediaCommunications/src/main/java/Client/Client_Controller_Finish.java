package Client;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Client_Controller_Finish implements Initializable {

    public Button closeWindow;
    public MediaView media;

    public void exit(ActionEvent actionEvent) throws IOException {
        //close the thread
        Stage stage = (Stage) closeWindow.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File mFile = new File("/home/xaris/Desktop/repos/Uni/MultiComms_lab/FFmpeg_videos/fetched_videos/Alicia Keys & Jack White - Another Way To Die [Official Video]-0.5Mbps.mp4");
        Media mMedia = new Media(mFile.toURI().toString());
        MediaPlayer player = new MediaPlayer(mMedia);
        media.setMediaPlayer(player);
    }
}
