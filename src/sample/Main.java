package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/* =====================================================================================================================
MAIN CLASS CODED PRIMARILY BY TRISTEN
===================================================================================================================== */
public class Main extends Application {

    // Global variables to set when logged in (T)
    public static boolean loggedIn = false;
    public static int loggedInAgentId = 0;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        root.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setTitle("Travel Experts - Manager");
        primaryStage.setScene(new Scene(root));
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.show();

    }

    //Getter and setters for login (T)
    public static void setLoggedIn(boolean isLoggedIn)
    {
        loggedIn = isLoggedIn;
    }
    public static boolean getLoggedIn()
    {
        return loggedIn;
    }

    public static void setLoggedInAgentId(int agentId)
    {
        loggedInAgentId = agentId;
    }
    public static int getLoggedInAgentId()
    {
        return loggedInAgentId;
    }
}
