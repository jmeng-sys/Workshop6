package sample;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller {

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
    void initialize() {
        assert btnUser != null : "fx:id=\"btnUser\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnReports != null : "fx:id=\"btnReports\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnSupplier != null : "fx:id=\"btnSupplier\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnProducts != null : "fx:id=\"btnProducts\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnPackages != null : "fx:id=\"btnPackages\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnPrint != null : "fx:id=\"btnPrint\" was not injected: check your FXML file 'sample.fxml'.";
        assert btnOptions != null : "fx:id=\"btnPrint1\" was not injected: check your FXML file 'sample.fxml'.";


        btnReports.setOnMouseClicked(event -> {
            System.out.println("on route to print Table");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("PrintTable.fxml"));

                Stage stage = (Stage) btnReports.getScene().getWindow();
                Scene scene = new Scene(loader.load());
                scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
                stage.setScene(scene);
            }catch (IOException io){
                io.printStackTrace();
            }

        });

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
    }

}
