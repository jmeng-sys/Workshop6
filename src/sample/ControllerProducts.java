package sample;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ControllerProducts {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private FontAwesomeIcon btnPrint;

    @FXML
    private FontAwesomeIcon btnOptions;

    @FXML
    private FontAwesomeIcon btnHome;

    @FXML
    private FontAwesomeIcon btnLogin;

    @FXML
    private FontAwesomeIcon btnUser;

    @FXML
    private Label lblAgentName;

    @FXML
    private Label dateTime;

    @FXML
    private FontAwesomeIcon btnExit;

    @FXML
    void initialize() {
        assert btnPrint != null : "fx:id=\"btnPrint\" was not injected: check your FXML file 'Products.fxml'.";
        assert btnOptions != null : "fx:id=\"btnOptions\" was not injected: check your FXML file 'Products.fxml'.";
        assert btnHome != null : "fx:id=\"btnHome\" was not injected: check your FXML file 'Products.fxml'.";
        assert btnLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'Products.fxml'.";
        assert btnUser != null : "fx:id=\"btnUser\" was not injected: check your FXML file 'Products.fxml'.";
        assert lblAgentName != null : "fx:id=\"lblAgentName\" was not injected: check your FXML file 'Products.fxml'.";
        assert dateTime != null : "fx:id=\"dateTime\" was not injected: check your FXML file 'Products.fxml'.";
        assert btnExit != null : "fx:id=\"btnExit\" was not injected: check your FXML file 'Products.fxml'.";

        btnExit.setOnMouseClicked(mouseEvent -> System.exit(0));
        btnHome.setOnMouseClicked(event -> {
            redirectToHome();
        });
    }

    private void redirectToHome()
    {
        System.out.println("on route to sample");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
            Stage stage = (Stage) btnHome.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            stage.setScene(scene);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
