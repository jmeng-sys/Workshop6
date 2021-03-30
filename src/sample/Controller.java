package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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

        btnTables.setOnMouseClicked(event -> {
            System.out.println("on route to print Table");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("PrintTable.fxml"));

                Stage stage = (Stage) btnTables.getScene().getWindow();
                Scene scene = new Scene(loader.load());
                scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
                stage.setScene(scene);
            }catch (IOException io){
                io.printStackTrace();
            }

        });
    }
}
