package sample;

import database.DAO;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import objects.GUIMethods;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControllerPrintTable {
    private ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();

    @FXML
    private Label dateTime;

    @FXML
    private ComboBox<String> cbTables;

    @FXML
    private ResourceBundle resources;

    @FXML
    private FontAwesomeIcon btnExit;

    @FXML
    private FontAwesomeIcon btnNext;

    @FXML
    private FontAwesomeIcon btnPrev;

    @FXML
    private FontAwesomeIcon btnHome;

    @FXML
    private FontAwesomeIcon btnPrint;

    @FXML
    private FontAwesomeIcon btnUser;

    @FXML
    private FontAwesomeIcon btnOptions;

    @FXML
    private FontAwesomeIcon btnLogin;

    @FXML
    private Label lblAgentName;

    @FXML
    private TableView<ObservableList<String>> tableDatabase;

    @FXML
    void initialize() {
        assert cbTables != null : "fx:id=\"cbTables\" was not injected: check your FXML file 'PrintTable.fxml'.";
        assert btnUser != null : "fx:id=\"btnUser\" was not injected: check your FXML file 'PrintTable.fxml'.";
        assert btnOptions != null : "fx:id=\"btnOptions\" was not injected: check your FXML file 'PrintTable.fxml'.";
        assert btnHome != null : "fx:id=\"btnTables\" was not injected: check your FXML file 'PrintTable.fxml'.";
        assert btnExit != null : "fx:id=\"btnExit\" was not injected: check your FXML file 'PrintTable.fxml'.";
        assert btnPrint != null : "fx:id=\"btnOptions1\" was not injected: check your FXML file 'PrintTable.fxml'.";
        assert btnPrev != null : "fx:id=\"btnPrev\" was not injected: check your FXML file 'PrintTable.fxml'.";
        assert btnNext != null : "fx:id=\"btnNext\" was not injected: check your FXML file 'PrintTable.fxml'.";
        assert dateTime != null : "fx:id=\"dateTime\" was not injected: check your FXML file 'PrintTable.fxml'.";
        assert tableDatabase != null : "fx:id=\"tableDatabase\" was not injected: check your FXML file 'PrintTable.fxml'.";
        assert btnLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'PrintTable.fxml'.";
        assert lblAgentName != null : "fx:id=\"lblAgentName\" was not injected: check your FXML file 'PrintTable.fxml'.";
//DASHBOARD BUTTONS ====================================================================================================
        btnExit.setOnMouseClicked(mouseEvent -> System.exit(0));
        btnPrint.setOnMouseClicked(event -> GetPrintScene());
        btnOptions.setOnMouseClicked(event -> GetOptionsScene());
        btnLogin.setOnMouseClicked(event -> GetLoginsScene());
        btnHome.setOnMouseClicked(event -> GetHomeScene());
//      btnUser.setOnMouseClicked(event -> { GetUserScene(); });
// DASHBOARD METHODS ===================================================================================================
        DashboardMethods.changeAgentName(lblAgentName);
        GUIMethods.GetDateTime(dateTime);
//  PRINT TABLE BUTTONS =================================================================================================
        btnExit.setOnMouseClicked(mouseEvent -> System.exit(0));
        btnNext.setOnMouseClicked(mouseEvent -> cbTables.getSelectionModel().selectNext());
        btnPrev.setOnMouseClicked(mouseEvent -> cbTables.getSelectionModel().selectPrevious());
//  SET COMBO BOX LIST AND LISTENERS ===================================================================================
        getAllTableNames();
//  BUILD TABLE AND COLUMNS ============================================================================================
        cbTables.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> getTableColumns(t1));
        cbTables.getSelectionModel().select(0);
// PRINT BUTTON ========================================================================================================
        btnPrint.setOnMouseClicked(e -> {
            ChoiceDialog<Printer> dialog = new ChoiceDialog<>(Printer.getDefaultPrinter(), Printer.getAllPrinters());
            dialog.setHeaderText("Choose the printer!");
            dialog.setContentText("Choose a printer from available printers");
            dialog.setTitle("Printer Choice");
            Optional<Printer> opt = dialog.showAndWait();
            if (opt.isPresent()) {
                PrinterJob job = PrinterJob.createPrinterJob(opt.get());
                Stage dialogStage = (Stage) btnHome.getScene().getWindow();
                boolean showDialog = job.showPrintDialog(dialogStage);
                if (showDialog) {
                    tableDatabase.setScaleX(0.60);
                    tableDatabase.setScaleY(0.60);
                    tableDatabase.setTranslateX(-220);
                    tableDatabase.setTranslateY(-70);
                    boolean success = job.printPage(tableDatabase);
                    if (success) {
                        job.endJob();
                    }
                    tableDatabase.setTranslateX(0);
                    tableDatabase.setTranslateY(0);
                    tableDatabase.setScaleX(1.0);
                    tableDatabase.setScaleY(1.0);
                }
            }
        });
    }

    private void getTableColumns(String t1) {
        tableDatabase.getItems().clear();
        tableDatabase.getColumns().clear();
        if (tableDatabase.getItems().isEmpty()) {
            data = FXCollections.observableArrayList();
            try {
                Connection conn = DAO.getConnection();
                String dataQuery = "SELECT * FROM " + t1;
                ControllerAgent.queryTableData(conn, dataQuery, tableDatabase, data);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void getAllTableNames() { getAllTableNames(cbTables); }

    static void getAllTableNames(ComboBox<String> cbTables) {
        try {
            DatabaseMetaData databaseMetaData = DAO.getConnection().getMetaData();
            ResultSet tables = databaseMetaData.getTables(
                    "TRAVELEXPERTS",
                    "1",
                    null,
                    new String[]{"TABLE"});
            ObservableList<String> tableList = FXCollections.observableArrayList();
            while (tables.next()) { tableList.add(tables.getString("TABLE_NAME")); }
            cbTables.setItems(tableList);
        } catch (SQLException e) { e.printStackTrace(); }
    }

// DASHBOARD METHODS ===================================================================================================
    private void GetOptionsScene() { DashboardMethods.IconGetScene("SystemDiagnostics.fxml", btnOptions); }
    private void GetLoginsScene() { DashboardMethods.IconGetScene("Login.fxml", btnLogin); }
    private void GetHomeScene() { DashboardMethods.IconGetScene("Home.fxml", btnHome); }
    private void GetPrintScene() { DashboardMethods.IconGetScene("PrintTable.fxml", btnPrint); }
//    private void GetUserScene() { DashboardMethods.IconGetScene("User.fxml", btnUser); }
}





