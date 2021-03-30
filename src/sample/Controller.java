package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnOptions;

    @FXML
    private Button btnTables;

    @FXML
    private Button btnExit;

    @FXML
    void initialize() {
        assert btnOptions != null : "fx:id=\"btnOptions\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnTables != null : "fx:id=\"btnTables\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnExit != null : "fx:id=\"btnExit\" was not injected: check your FXML file 'sample.fxml'.";

    }
}
