package sample;

import database.AgentAccountsDB;
import database.DAO;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import objects.Agent;
import objects.GUIMethods;
import sample.Controller;

public class ControllerLogin {

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
    private Label dateTime;

    @FXML
    private FontAwesomeIcon btnExit;

    @FXML
    private Label lblForgotPassword;

    @FXML
    private TextField tfUsername;

    @FXML
    private TextField tfPassword;

    @FXML
    private Label lblCreateAccount;

    @FXML
    private Button btnAgentLogin;

    @FXML
    private Label lblBadLogin;

    @FXML
    private VBox vbLogin;

    @FXML
    void initialize() {
        assert btnUser != null : "fx:id=\"btnUser\" was not injected: check your FXML file 'Login.fxml'.";
//        assert btnOptions != null : "fx:id=\"btnOptions\" was not injected: check your FXML file 'Login.fxml'.";
        assert btnHome != null : "fx:id=\"btnTables\" was not injected: check your FXML file 'Login.fxml'.";
        assert btnExit != null : "fx:id=\"btnExit\" was not injected: check your FXML file 'Login.fxml'.";
        assert btnPrint != null : "fx:id=\"btnOptions1\" was not injected: check your FXML file 'Login.fxml'.";
        assert dateTime != null : "fx:id=\"dateTime\" was not injected: check your FXML file 'Login.fxml'.";

        assert lblForgotPassword != null : "fx:id=\"lblForgotPassword\" was not injected: check your FXML file 'Login.fxml'.";
        assert tfUsername != null : "fx:id=\"tfUsername\" was not injected: check your FXML file 'Login.fxml'.";
        assert tfPassword != null : "fx:id=\"tfPassword\" was not injected: check your FXML file 'Login.fxml'.";
        assert lblCreateAccount != null : "fx:id=\"lblCreateAccount\" was not injected: check your FXML file 'Login.fxml'.";
        assert btnAgentLogin != null : "fx:id=\"btnAgentLogin\" was not injected: check your FXML file 'Login.fxml'.";
        assert lblBadLogin != null : "fx:id=\"lblBadLogin\" was not injected: check your FXML file 'Login.fxml'.";
        assert vbLogin != null : "fx:id=\"vbLogin\" was not injected: check your FXML file 'Login.fxml'.";

        final BooleanProperty firstFocus = new SimpleBooleanProperty(true);

        btnExit.setOnMouseClicked(mouseEvent -> System.exit(0));

        //Removes default focus from username field
        tfUsername.focusedProperty().addListener((observable, oldvalue, newValue) -> {
            if(newValue && firstFocus.get()) {
                vbLogin.requestFocus();
                firstFocus.setValue(false);
            }
        });

        btnHome.setOnMouseClicked(event -> {
            redirectToHome();
        });

        btnPrint.setOnMouseClicked(event -> {
            GetReportsScene();
        });

        btnAgentLogin.setOnMouseClicked(event -> {
            HandleLogin();
        });

        //Allows enter to be pressed when on the fields to submit login data
        tfUsername.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            if(keyCode.equals(KeyCode.ENTER))
            {
                System.out.println("Enter pressed");
                HandleLogin();
            }
        });
        tfPassword.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            if(keyCode.equals(KeyCode.ENTER))
            {
                System.out.println("Enter pressed");
                HandleLogin();
            }
        });

// SET DATE AND TIME OBJECT ============================================================================================
        GUIMethods.GetDateTime(dateTime);
    }

    private void GetReportsScene() {
        System.out.println("Loading Print Scene");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PrintTable.fxml"));

            Stage stage = (Stage) btnPrint.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            stage.setScene(scene);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    private void HandleLogin()
    {
        System.out.println("Login button clicked");
        //Take in values of textbox
        String username = tfUsername.getText();
        String password = tfPassword.getText();
        System.out.println("Username: " + username + " , Password: " + password);
        //Take in values of database?
        //Check to see if they match any entries in database
        try
        {
            Connection conn = DAO.getConnection();
            Statement myStmt = conn.createStatement();
            ResultSet rs = myStmt.executeQuery("Select * from agentaccounts where Username = \"" + username + "\" and Password = \"" + password + "\"");

            if(rs.next() == false)
            {
                System.out.println("Username or Password are incorrect");
                lblBadLogin.setVisible(true);
            }
            else
            {
                System.out.println("Username and Password are correct.");
                Main.setLoggedIn(true);
                Main.setLoggedInAgentId(rs.getInt(1));
                System.out.println("AgentID = " + rs.getInt(1));
                System.out.println("Logged in is: " + Main.getLoggedIn());
                lblBadLogin.setVisible(false);
                redirectToHome();
            }
            conn.close();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
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
