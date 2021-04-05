package sample;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import objects.GUIMethods;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller {

    @FXML
    private Label dateTime;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private FontAwesomeIcon btnUser;

    @FXML
    private HBox btnReports;

    @FXML
    private HBox btnSupplier;

    @FXML
    private HBox btnProducts;

    @FXML
    private HBox btnPackages;

    @FXML
    private FontAwesomeIcon btnPrint;

    @FXML
    private FontAwesomeIcon btnOptions;

    @FXML
    private FontAwesomeIcon btnLogin;

    @FXML
    private FontAwesomeIcon btnExit;

    @FXML
    void initialize() {
        assert btnUser != null : "fx:id=\"btnUser\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnReports != null : "fx:id=\"btnReports\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnSupplier != null : "fx:id=\"btnSupplier\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnProducts != null : "fx:id=\"btnProducts\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnPackages != null : "fx:id=\"btnPackages\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnPrint != null : "fx:id=\"btnPrint\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnOptions != null : "fx:id=\"btnPrint1\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnExit != null : "fx:id=\"btnExit\" was not injected: check your FXML file 'sample.fxml'.";
        assert dateTime != null : "fx:id=\"dateTime\" was not injected: check your FXML file 'sample.fxml'.";
//MENU BUTTONS
        btnExit.setOnMouseClicked(mouseEvent -> System.exit(0));
// NAVIGATE TO REPORTS =================================================================================================
        btnReports.setOnMouseClicked(event -> {
            GetReportsScene();
        });

        btnPrint.setOnMouseClicked(event -> {
            GetReportsScene();
        });
// NAVIGATE TO LOGIN
        btnLogin.setOnMouseClicked(event -> {
            GetLoginsScene();
        });

//NAVIGATE TO PACKAGES
        btnPackages.setOnMouseClicked(event -> {
            System.out.println("on route to packages");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Packages.fxml"));

                Stage stage = (Stage) btnPackages.getScene().getWindow();
                Scene scene = new Scene(loader.load());
                scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
                stage.setScene(scene);
            }catch (IOException io){
                io.printStackTrace();
            }

        });

// SET DATE AND TIME OBJECT ============================================================================================
        GUIMethods.GetDateTime(dateTime);
    }


//    METHODS DEFINED ==================================================================================================
    private void GetReportsScene() {
        System.out.println("on route to print Table");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PrintTable.fxml"));

            Stage stage = (Stage) btnReports.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            stage.setScene(scene);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    private void GetLoginsScene() {
        System.out.println("Loading login scene");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));

            Stage stage = (Stage) btnLogin.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            stage.setScene(scene);
        } catch (IOException io) {
            io.printStackTrace();
        }

    }

}
