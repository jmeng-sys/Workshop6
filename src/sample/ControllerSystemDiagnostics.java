package sample;

import database.DAO;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import objects.GUIMethods;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class ControllerSystemDiagnostics implements DAO {
    private ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private FontAwesomeIcon btnPrint;

    @FXML
    private FontAwesomeIcon btnOptions;

    @FXML
    private FontAwesomeIcon btnLogin;

    @FXML
    private FontAwesomeIcon btnUser;

    @FXML
    private Label dateTime;

    @FXML
    private FontAwesomeIcon btnExit;

    @FXML
    private TextField txtDatabaseName;

    @FXML
    private TextField txtDBUsername;

    @FXML
    private TextField txtDBPassword;

    @FXML
    private FontAwesomeIcon btnTestConn;

    @FXML
    private Circle ConnRed;

    @FXML
    private Circle ConnYellow;

    @FXML
    private Circle ConnGreen;

    @FXML
    private TextField txtDBURL;

    @FXML
    private TextField txtDriver;

    @FXML
    private TextField txtDriverVersion;

    @FXML
    private TextField txtProductName;

    @FXML
    private TextField txtProductVersion;

    @FXML
    private ComboBox<String> cbTables;

    @FXML
    private TextArea tfTableHealth;

    @FXML
    private FontAwesomeIcon btnPrev;

    @FXML
    private FontAwesomeIcon btnNext;

    @FXML
    private FontAwesomeIcon iconHealth;

    @FXML
    private FontAwesomeIcon btnEditURL;

    @FXML
    private FontAwesomeIcon btnEditUsername;

    @FXML
    private FontAwesomeIcon btnEditPassword;

    @FXML
    private FontAwesomeIcon btnSaveURL;

    @FXML
    private FontAwesomeIcon btnSaveUsername;

    @FXML
    private FontAwesomeIcon btnSavePassword;



    @FXML
    void initialize() throws SQLException {
        assert btnPrint != null : "fx:id=\"btnPrint\" was not injected: check your FXML file 'SystemDiagnostics.fxml'.";
        assert btnOptions != null : "fx:id=\"btnOptions\" was not injected: check your FXML file 'SystemDiagnostics.fxml'.";
        assert btnLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'SystemDiagnostics.fxml'.";
        assert btnUser != null : "fx:id=\"btnUser\" was not injected: check your FXML file 'SystemDiagnostics.fxml'.";
        assert dateTime != null : "fx:id=\"dateTime\" was not injected: check your FXML file 'SystemDiagnostics.fxml'.";
        assert btnExit != null : "fx:id=\"btnExit\" was not injected: check your FXML file 'SystemDiagnostics.fxml'.";
        assert txtDatabaseName != null : "fx:id=\"txtDatabaseName\" was not injected: check your FXML file 'SystemDiagnostics.fxml'.";
        assert txtDBUsername != null : "fx:id=\"txtDBUsername\" was not injected: check your FXML file 'SystemDiagnostics.fxml'.";
        assert txtDBPassword != null : "fx:id=\"txtDBPassword\" was not injected: check your FXML file 'SystemDiagnostics.fxml'.";
        assert btnTestConn != null : "fx:id=\"btnTestConn\" was not injected: check your FXML file 'SystemDiagnostics.fxml'.";        assert ConnRed != null : "fx:id=\"ConnRed\" was not injected: check your FXML file 'SystemDiagnostics.fxml'.";
        assert ConnYellow != null : "fx:id=\"ConnYellow\" was not injected: check your FXML file 'SystemDiagnostics.fxml'.";
        assert ConnGreen != null : "fx:id=\"ConnGreen\" was not injected: check your FXML file 'SystemDiagnostics.fxml'.";
        assert txtDBURL != null : "fx:id=\"txtDBURL\" was not injected: check your FXML file 'SystemDiagnostics.fxml'.";
        assert txtDriver != null : "fx:id=\"txtDriver\" was not injected: check your FXML file 'SystemDiagnostics.fxml'.";
        assert txtDriverVersion != null : "fx:id=\"txtDriverVersion\" was not injected: check your FXML file 'SystemDiagnostics.fxml'.";
        assert txtProductName != null : "fx:id=\"txtProductName\" was not injected: check your FXML file 'SystemDiagnostics.fxml'.";
        assert txtProductVersion != null : "fx:id=\"txtProductVersion\" was not injected: check your FXML file 'SystemDiagnostics.fxml'.";
        assert cbTables != null : "fx:id=\"cbTables\" was not injected: check your FXML file 'SystemDiagnostics.fxml'.";
        assert tfTableHealth != null : "fx:id=\"tfTableHealth\" was not injected: check your FXML file 'SystemDiagnostics.fxml'.";
        assert btnPrev != null : "fx:id=\"btnPrev\" was not injected: check your FXML file 'SystemDiagnostics.fxml'.";
        assert btnNext != null : "fx:id=\"btnNext\" was not injected: check your FXML file 'SystemDiagnostics.fxml'.";
        assert iconHealth != null : "fx:id=\"iconHealth\" was not injected: check your FXML file 'SystemDiagnostics.fxml'.";
        assert btnEditURL != null : "fx:id=\"btnEditURL\" was not injected: check your FXML file 'SystemDiagnostics.fxml'.";
        assert btnEditUsername != null : "fx:id=\"btnEditUsername\" was not injected: check your FXML file 'SystemDiagnostics.fxml'.";
        assert btnEditPassword != null : "fx:id=\"btnEditPassword\" was not injected: check your FXML file 'SystemDiagnostics.fxml'.";
        assert btnSaveURL != null : "fx:id=\"btnSaveURL\" was not injected: check your FXML file 'SystemDiagnostics.fxml'.";
        assert btnSaveUsername != null : "fx:id=\"btnSaveUsername\" was not injected: check your FXML file 'SystemDiagnostics.fxml'.";
        assert btnSavePassword != null : "fx:id=\"btnSavePassword\" was not injected: check your FXML file 'SystemDiagnostics.fxml'.";


//MENU BUTTONS
        btnExit.setOnMouseClicked(mouseEvent -> System.exit(0));
// NAVIGATE TO REPORTS =================================================================================================
        btnPrint.setOnMouseClicked(event -> {
            GetReportsScene();
        });
// NAVIGATE TO LOGIN
        btnLogin.setOnMouseClicked(event -> {
            GetLoginsScene();
        });
// SET DATE AND TIME OBJECT ============================================================================================
        GUIMethods.GetDateTime(dateTime);
// SET SYSTEM DIAGNOSTICS ELEMENTS =====================================================================================
        btnPrev.setOnMouseClicked(event -> cbTables.getSelectionModel().selectPrevious());
        btnNext.setOnMouseClicked(event -> cbTables.getSelectionModel().selectNext());

        btnEditURL.setOnMouseClicked(event -> {
            txtDBURL.setEditable(true);
            btnEditURL.setVisible(false);
            btnSaveURL.setVisible(true);
        });

        btnEditPassword.setOnMouseClicked(event -> {
            txtDBPassword.setEditable(true);
            btnEditPassword.setVisible(false);
            btnSavePassword.setVisible(true);
        });

        btnEditUsername.setOnMouseClicked(event ->{
            txtDBUsername.setEditable(true);
            btnEditUsername.setVisible(false);
            btnSaveUsername.setVisible(true);
        });

        btnSaveURL.setOnMouseClicked(event -> {
            txtDBURL.setEditable(false);
            btnEditURL.setVisible(true);
            btnSaveURL.setVisible(false);
        });

        btnSavePassword.setOnMouseClicked(event -> {
            txtDBPassword.setEditable(false);
            btnEditPassword.setVisible(true);
            btnSavePassword.setVisible(false);
        });

        btnSaveUsername.setOnMouseClicked(event ->{
            txtDBUsername.setEditable(false);
            btnEditUsername.setVisible(true);
            btnSaveUsername.setVisible(false);
        });




        try {
            txtDatabaseName.setText(DAO.getConnection().getCatalog());
        } catch (Exception e) {
            txtDatabaseName.setText("Connection Failed");
            e.printStackTrace();
        }
        txtDBUsername.setText(DAO.db_username);
        txtDBPassword.setText(DAO.db_password);
        txtDBURL.setText(DAO.db_url);

        btnTestConn.setOnMouseClicked(event -> {
            btnTestConn.setDisable(true);
            try {
                DAO.getConnection().beginRequest();
                ConnYellow.setFill(Color.YELLOW);
            } catch (Exception e) {
                ConnRed.setFill(Color.RED);
                e.printStackTrace();
            }
            try {
                DAO.getConnection().endRequest();
                ConnYellow.setFill(Color.BLACK);
                ConnGreen.setFill(Color.GREEN);
            } catch (Exception e) {
                ConnRed.setFill(Color.RED);
                e.printStackTrace();
            }

            Timer timer = new Timer(true);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    ConnRed.setFill(Color.BLACK);
                    ConnYellow.setFill(Color.BLACK);
                    ConnGreen.setFill(Color.BLACK);
                    btnTestConn.setDisable(false);
                }
            }, 3000);

        });

        try {
            Connection conn = DAO.getConnection();
            DatabaseMetaData meta = conn.getMetaData();
            txtProductName.setText(meta.getDatabaseProductName());
            txtProductVersion.setText(meta.getDatabaseProductVersion());
            txtDriver.setText(meta.getDriverName());
            txtDriverVersion.setText(meta.getDriverVersion());
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            DatabaseMetaData databaseMetaData = DAO.getConnection().getMetaData();
            ResultSet tables = databaseMetaData.getTables(
                    "TRAVELEXPERTS",
                    "1",
                    null,
                    new String[]{"TABLE"});

            ObservableList<String> tableList = FXCollections.observableArrayList();

            while (tables.next()) {
                tableList.add(tables.getString("TABLE_NAME"));
            }
            cbTables.setItems(tableList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cbTables.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            tfTableHealth.clear();
            data = FXCollections.observableArrayList();
            String sql = "CHECK TABLE " + t1;
            Connection conn = DAO.getConnection();
            Statement stmt = null;
            try {
                stmt = conn.createStatement();
                ResultSet resultSet = stmt.executeQuery(sql);

                while (resultSet.next()) {
                    ObservableList<String> row = FXCollections.observableArrayList();
                    for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                        row.add(resultSet.getString(i));
                    }
                    if (resultSet.getString(4).equals("OK")) {
                        iconHealth.setFill(Color.GREEN);
                    }
                    else {
                        iconHealth.setFill(Color.RED);
                    }
                    data.add(row);
                }
                tfTableHealth.setText(data.toString());
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });






    }
//    CLASS CONTROLLERSYSTEMDIAGNOSTICS ENDS HERE ======================================================================










//    METHODS DEFINED ==================================================================================================
        private void GetReportsScene() {
            System.out.println("on route to print Table");
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
