package sample;

import database.DAO;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerPrintTable {
    private ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> cbTables;

    @FXML
    private Button btnOptions;

    @FXML
    private Button btnTables;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnPrint;

    @FXML
    private Button btnPrev;

    @FXML
    private Button btnNext;

    @FXML
    private TableView<ObservableList<String>> tableDatabase;

    @FXML
    void initialize() {
        assert cbTables != null : "fx:id=\"cbTables\" was not injected: check your FXML file 'PrintTable.fxml'.";
        assert btnOptions != null : "fx:id=\"btnOptions\" was not injected: check your FXML file 'PrintTable.fxml'.";
        assert btnTables != null : "fx:id=\"btnTables\" was not injected: check your FXML file 'PrintTable.fxml'.";
        assert btnExit != null : "fx:id=\"btnExit\" was not injected: check your FXML file 'PrintTable.fxml'.";
        assert btnPrint != null : "fx:id=\"btnOptions1\" was not injected: check your FXML file 'PrintTable.fxml'.";
        assert btnPrev != null : "fx:id=\"btnPrev\" was not injected: check your FXML file 'PrintTable.fxml'.";
        assert btnNext != null : "fx:id=\"btnNext\" was not injected: check your FXML file 'PrintTable.fxml'.";
        assert tableDatabase != null : "fx:id=\"tableDatabase\" was not injected: check your FXML file 'PrintTable.fxml'.";


//  MENU BAR BUTTONS ===================================================================================================
        btnExit.setOnMouseClicked(mouseEvent -> System.exit(0));
        btnNext.setOnMouseClicked(mouseEvent -> cbTables.getSelectionModel().selectNext());
        btnPrev.setOnMouseClicked(mouseEvent -> cbTables.getSelectionModel().selectPrevious());
//  SET SCENE WHEN BUTTON CLICKED ======================================================================================
        btnTables.setOnMouseClicked(event -> {
            System.out.println("on route to sample");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
                Stage stage = (Stage) btnTables.getScene().getWindow();
                Scene scene = new Scene(loader.load());
                scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
                stage.setScene(scene);
            } catch (IOException io) {
                io.printStackTrace();
            }
        });
//  SET COMBO BOX LIST AND LISTENERS ===================================================================================
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
//  BUILD TABLE AND COLUMNS ============================================================================================
        cbTables.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {

            tableDatabase.getItems().clear();
            tableDatabase.getColumns().clear();
            if (tableDatabase.getItems().isEmpty()) {
                data = FXCollections.observableArrayList();
                try {
                    Connection conn = DAO.getConnection();
                    String dataQuery = "SELECT * FROM " + t1;
                    ResultSet tableValues = conn.createStatement().executeQuery(dataQuery);

                    for (int i = 0; i < tableValues.getMetaData().getColumnCount(); i++) {
                        final int j = i;
                        TableColumn<ObservableList<String>, String> tableColumn = new TableColumn<>(tableValues.getMetaData().getColumnName(i + 1));
                        tableColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(j)));
                        tableDatabase.getColumns().add(tableColumn);
                    }
                    while (tableValues.next()) {
                        ObservableList<String> row = FXCollections.observableArrayList();
                        for (int i = 1; i <= tableValues.getMetaData().getColumnCount(); i++) {
                            row.add(tableValues.getString(i));
                        }
                        data.add(row);
                    }
                    tableDatabase.getItems().addAll(data);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
// PRINT BUTTON ========================================================================================================
        btnPrint.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){
                Stage dialogStage = (Stage) btnPrint.getScene().getWindow();

                PrinterJob printerJob = PrinterJob.createPrinterJob();
                if (printerJob.showPrintDialog(dialogStage) && printerJob.printPage(tableDatabase)) {
                    boolean showDialog = printerJob.showPageSetupDialog(dialogStage);
                    if (showDialog) {
                        tableDatabase.setScaleX(0.60);
                        tableDatabase.setScaleY(0.60);
                        tableDatabase.setTranslateX(-220);
                        tableDatabase.setTranslateY(-70);
                        boolean success = printerJob.printPage(tableDatabase);
                        if (success) {
                            printerJob.endJob();
                        }
                        tableDatabase.setTranslateX(0);
                        tableDatabase.setTranslateY(0);
                        tableDatabase.setScaleX(1.0);
                        tableDatabase.setScaleY(1.0);
                    }
                }
            }
        });
    }
}





