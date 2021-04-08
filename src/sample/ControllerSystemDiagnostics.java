package sample;

import database.DAO;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import objects.GUIMethods;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Timer;
import java.util.TimerTask;

public class ControllerSystemDiagnostics {
    private ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();

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
    private TextField txtBackupLocation;

    @FXML
    private FontAwesomeIcon btnBackupEdit;

    @FXML
    private FontAwesomeIcon btnBackupSave;

    @FXML
    private FontAwesomeIcon btnBackup;

    @FXML
    private FontAwesomeIcon iconBackup;

    @FXML
    private FontAwesomeIcon iconBackupFailed;

    @FXML
    void initialize() {
        assert btnPrint != null : "fx:id=\"btnPrint\" was not injected: check your FXML file 'SystemDiagnostics.fxml'.";
        assert btnOptions != null : "fx:id=\"btnOptions\" was not injected: check your FXML file 'SystemDiagnostics.fxml'.";
        assert btnLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'SystemDiagnostics.fxml'.";
        assert btnUser != null : "fx:id=\"btnUser\" was not injected: check your FXML file 'SystemDiagnostics.fxml'.";
        assert dateTime != null : "fx:id=\"dateTime\" was not injected: check your FXML file 'SystemDiagnostics.fxml'.";
        assert btnExit != null : "fx:id=\"btnExit\" was not injected: check your FXML file 'SystemDiagnostics.fxml'.";
        assert txtDatabaseName != null : "fx:id=\"txtDatabaseName\" was not injected: check your FXML file 'SystemDiagnostics.fxml'.";
        assert txtDBUsername != null : "fx:id=\"txtDBUsername\" was not injected: check your FXML file 'SystemDiagnostics.fxml'.";
        assert txtDBPassword != null : "fx:id=\"txtDBPassword\" was not injected: check your FXML file 'SystemDiagnostics.fxml'.";
        assert btnTestConn != null : "fx:id=\"btnTestConn\" was not injected: check your FXML file 'SystemDiagnostics.fxml'.";
        assert ConnRed != null : "fx:id=\"ConnRed\" was not injected: check your FXML file 'SystemDiagnostics.fxml'.";
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
        assert txtBackupLocation != null : "fx:id=\"txtBackupLocation\" was not injected: check your FXML file 'SystemDiagnostics.fxml'.";
        assert btnBackupEdit != null : "fx:id=\"btnBackupEdit\" was not injected: check your FXML file 'SystemDiagnostics.fxml'.";
        assert btnBackupSave != null : "fx:id=\"btnBackupSave\" was not injected: check your FXML file 'SystemDiagnostics.fxml'.";
        assert btnBackup != null : "fx:id=\"btnBackup\" was not injected: check your FXML file 'SystemDiagnostics.fxml'.";
        assert iconBackup != null : "fx:id=\"iconBackup\" was not injected: check your FXML file 'SystemDiagnostics.fxml'.";
        assert iconBackupFailed != null : "fx:id=\"iconBackupFailed\" was not injected: check your FXML file 'SystemDiagnostics.fxml'.";

//MENU BUTTONS
        btnExit.setOnMouseClicked(mouseEvent -> System.exit(0));
// NAVIGATE TO REPORTS =================================================================================================
        btnPrint.setOnMouseClicked(event -> GetReportsScene());
// NAVIGATE TO LOGIN
        btnLogin.setOnMouseClicked(event -> GetLoginsScene());
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

        btnEditUsername.setOnMouseClicked(event -> {
            txtDBUsername.setEditable(true);
            btnEditUsername.setVisible(false);
            btnSaveUsername.setVisible(true);
        });

        btnSaveURL.setOnMouseClicked(event -> {
            txtDBURL.setEditable(false);
            btnEditURL.setVisible(true);
            btnSaveURL.setVisible(false);

            DAO.db_username = txtDBUsername.getText();
        });

        btnSavePassword.setOnMouseClicked(event -> {
            txtDBPassword.setEditable(false);
            btnEditPassword.setVisible(true);
            btnSavePassword.setVisible(false);

            DAO.db_password = txtDBPassword.getText();
        });

        btnSaveUsername.setOnMouseClicked(event -> {
            txtDBUsername.setEditable(false);
            btnEditUsername.setVisible(true);
            btnSaveUsername.setVisible(false);

            DAO.db_username = txtDBUsername.getText();
        });

        btnBackupEdit.setOnMouseClicked(event -> {
            txtBackupLocation.setEditable(true);
            btnBackupSave.setVisible(true);
            btnBackupEdit.setVisible(false);
        });

        btnBackupSave.setOnMouseClicked(event -> {
            txtBackupLocation.setEditable(false);
            btnBackupSave.setVisible(false);
            btnBackupEdit.setVisible(true);
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

        ControllerPrintTable.getAllTableNames(cbTables);
        cbTables.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            tfTableHealth.clear();
            data = FXCollections.observableArrayList();
            String sql = "CHECK TABLE " + t1;
            Connection conn = DAO.getConnection();
            Statement stmt;
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
                        Timer timer = new Timer(true);
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                iconHealth.setFill(Color.BLACK);
                            }
                        }, 3000);
                    } else {
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

        btnBackup.setOnMouseClicked(event -> {
            try {
                String dbName = txtDatabaseName.getText();
                String dbUser = DAO.db_username;
//                String dbPass = DAO.db_password;  // add back in if password is included
                String folderPath = txtBackupLocation.getText() + "\\backup";
                File f1 = new File(folderPath);
                f1.mkdir();
                String savePath = folderPath + "\\" + "backup.sql\"";
                String executeCmd = "C:\\xampp\\mysql\\bin\\mysqldump -u" + dbUser + " --database " + dbName + " -r " + savePath;
                //      add this back in if password is included          " -p" + dbPass +
                Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
                int processComplete = runtimeProcess.waitFor();
                if (processComplete == 0) {
                    System.out.println("Backup Complete");
                    iconBackup.setVisible(true);

                    Timer timer = new Timer(true);
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            iconBackup.setVisible(false);
                        }
                    }, 3000);

                } else {
                    System.out.println("Backup Failure");
                    iconBackupFailed.setVisible(true);
                    Timer timer = new Timer(true);
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            iconBackupFailed.setVisible(false);
                        }
                    }, 3000);
                }
            } catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Error at Backup restore" + ex.getMessage());
                alert.showAndWait();
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
