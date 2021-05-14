package sample;

/*
    Login Controller Class
    Handles all of the login functions
    Code by Tristen Stockley (T)
 */

import database.DAO;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ControllerLogin {

    //Declares scene builder fields (T)
    @FXML private Label lblForgotPassword;
    @FXML private TextField tfUsername;
    @FXML private TextField tfPassword;
    @FXML private Label lblCreateAccount;
    @FXML private Button btnAgentLogin;
    @FXML private Label lblBadLogin;
    @FXML private VBox vbLogin;

    @FXML
    void initialize()
    {
        //Initializes all scene builder fields (T)
        assert lblForgotPassword != null : "fx:id=\"lblForgotPassword\" was not injected: check your FXML file 'Login.fxml'.";
        assert tfUsername != null : "fx:id=\"tfUsername\" was not injected: check your FXML file 'Login.fxml'.";
        assert tfPassword != null : "fx:id=\"tfPassword\" was not injected: check your FXML file 'Login.fxml'.";
        assert lblCreateAccount != null : "fx:id=\"lblCreateAccount\" was not injected: check your FXML file 'Login.fxml'.";
        assert btnAgentLogin != null : "fx:id=\"btnAgentLogin\" was not injected: check your FXML file 'Login.fxml'.";
        assert lblBadLogin != null : "fx:id=\"lblBadLogin\" was not injected: check your FXML file 'Login.fxml'.";
        assert vbLogin != null : "fx:id=\"vbLogin\" was not injected: check your FXML file 'Login.fxml'.";

        //Removes default focus from username field (T)
        final BooleanProperty firstFocus = new SimpleBooleanProperty(true);
        tfUsername.focusedProperty().addListener((observable, oldvalue, newValue) -> {
            if(newValue && firstFocus.get()) {
                vbLogin.requestFocus();
                firstFocus.setValue(false);
            }
        });

        //Submits data on click of login button (T)
        btnAgentLogin.setOnMouseClicked(event -> { HandleLogin(); });

        //Allows enter to be pressed when on the fields to submit login data (T)
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
    }

    //Handles the login for the user (T)
    private void HandleLogin()
    {
        System.out.println("Login button clicked");
        String username = tfUsername.getText();
        String password = tfPassword.getText();
        System.out.println("Username: " + username + " , Password: " + password);
        try
        {
            Connection conn = DAO.getConnection();
            Statement myStmt = conn.createStatement();
            @SuppressWarnings("SqlResolve") ResultSet rs = myStmt.executeQuery("Select * from agentaccounts where Username = \"" + username + "\" and Password = \"" + password + "\"");

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

    //Redirects to the home dashboard (T)
    private void redirectToHome()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
            Stage stage = (Stage) btnAgentLogin.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(DashboardMethods.class.getResource("style.css").toExternalForm());
            stage.setScene(scene);
        }
        catch (IOException io)
        {
            io.printStackTrace();
        }
    }
}