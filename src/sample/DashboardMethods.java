package sample;

import database.DAO;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DashboardMethods {

//METHOD FOR CARDS ON MAIN PAGE TO LOAD SCENES ========================================================================
    public static void HboxGetScene(String s, HBox b) {
        try {
            FXMLLoader loader = new FXMLLoader(DashboardMethods.class.getResource(s));

            Stage stage = (Stage) b.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(DashboardMethods.class.getResource("style.css").toExternalForm());
            stage.setScene(scene);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

//METHOD FOR ICONS ON DASHBOARD TO LOAD SCENES ========================================================================
    public static void IconGetScene(String s, FontAwesomeIcon b) {
        try {
            FXMLLoader loader = new FXMLLoader(DashboardMethods.class.getResource(s));

            Stage stage = (Stage) b.getScene().getWindow();
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(DashboardMethods.class.getResource("style.css").toExternalForm());
            stage.setScene(scene);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

//METHOD CALL TO CHANGE LABEL WITH NAME OF AGENT LOGGED IN
    public static void changeAgentName(Label lblAgentName)
    {
        if(Main.getLoggedIn())
        {
            try
            {
                Connection conn = DAO.getConnection();
                Statement myStmt = conn.createStatement();
                ResultSet rs = myStmt.executeQuery("Select AgtFirstName, AgtLastName from agents where AgentId = " + Main.getLoggedInAgentId());
                rs.next();
                System.out.println(rs.getString(1));
                System.out.println(rs.getString(2));
                lblAgentName.setText(rs.getString(1) + " " + rs.getString(2));
            }
            catch (SQLException throwables)
            {
                throwables.printStackTrace();
            }
        }
    }





}
