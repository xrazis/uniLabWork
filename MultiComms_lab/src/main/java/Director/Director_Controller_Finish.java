package Director;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Director_Controller_Finish {

    public Button closeWindow;

    public void exit(ActionEvent actionEvent) throws IOException {
        Director_Global_Data.deleteFiles();
        Stage stage = (Stage) closeWindow.getScene().getWindow();
        stage.close();
    }
}
