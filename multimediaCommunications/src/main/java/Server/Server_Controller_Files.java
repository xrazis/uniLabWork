package Server;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Server_Controller_Files implements Initializable {

    public Button closeWindow;
    public ListView<String> filesToDisplay;

    Runnable mServer = () -> Server_Global_Data.startServer(5000);

    public void exit(ActionEvent actionEvent) throws IOException {
        //close the thread
        Stage stage = (Stage) closeWindow.getScene().getWindow();
        stage.close();
    }

    public void startServer() {
        new Thread(mServer).start();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        filesToDisplay.setItems(FXCollections.observableArrayList(Server_Global_Data.videos));
    }
}
