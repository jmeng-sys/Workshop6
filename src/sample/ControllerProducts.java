package sample;

import database.DAO;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import objects.Packages;
import objects.Product;

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
    private ComboBox<?> cbProducts;

    @FXML
    private TextField tfProductName;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnSave;

    @FXML
    private TableView<Product> tblProducts;

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
        assert cbProducts != null : "fx:id=\"cbProducts\" was not injected: check your FXML file 'Products.fxml'.";
        assert tfProductName != null : "fx:id=\"tfProductName\" was not injected: check your FXML file 'Products.fxml'.";
        assert btnEdit != null : "fx:id=\"btnEdit\" was not injected: check your FXML file 'Products.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'Products.fxml'.";
        assert tblProducts != null : "fx:id=\"tblProducts\" was not injected: check your FXML file 'Products.fxml'.";

        btnExit.setOnMouseClicked(mouseEvent -> System.exit(0));
        btnHome.setOnMouseClicked(event -> {
            redirectToHome();
        });

        /* WORK IN PROGRESS
        try {
            Connection conn = DAO.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from products");

            ObservableList<Packages> pkgs = FXCollections.observableArrayList();
            while (rs.next()) {
                pkgs.add(new Packages(rs.getInt(1), rs.getString(2), rs.getTimestamp(3),
                        rs.getTimestamp(4), rs.getString(5), rs.getDouble(6),
                        rs.getDouble(7)));
            }

            //cbPkg.setItems(pkgs);

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

         */
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
