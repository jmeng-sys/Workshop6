package sample;

import database.DAO;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import objects.GUIMethods;
import objects.Product;
import objects.Supplier;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControllerProducts {

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
    private Label lblError;

    @FXML
    private FontAwesomeIcon btnExit;


    @FXML
    private TextField tfProductId;

    @FXML
    private TextField tfProductName;

    @FXML
    private TableView<ObservableList<String>> tblProducts;

    @FXML
    private FontAwesomeIcon btnAdd;

    @FXML
    private FontAwesomeIcon btnModify;

    @FXML
    private FontAwesomeIcon btnSave;

    @FXML
    private FontAwesomeIcon btnCancel;

    @FXML FontAwesomeIcon btnDelete;

    @FXML
    private TableView<ObservableList<String>> tblProdSupp;

    @FXML
    private ComboBox<Product> cbFilterByProd;

    @FXML
    private ComboBox<Supplier> cbFilterBySupp;

    @FXML
    private Button btnClearFilter;

    //SQL strings for filters
    private String prodNameFilter = "";
    private String suppNameFilter = "";

    //Boolean for add/modify
    private boolean isAdd = true;
    int currentProdId = 0;

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
        assert tfProductId != null : "fx:id=\"tfProductId\" was not injected: check your FXML file 'Products.fxml'.";
        assert tfProductName != null : "fx:id=\"tfProductName\" was not injected: check your FXML file 'Products.fxml'.";
        assert tblProducts != null : "fx:id=\"tblProducts\" was not injected: check your FXML file 'Products.fxml'.";
        assert btnAdd != null : "fx:id=\"btnAdd\" was not injected: check your FXML file 'Products.fxml'.";
        assert btnModify != null : "fx:id=\"btnModify\" was not injected: check your FXML file 'Products.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'Products.fxml'.";
        assert cbFilterByProd != null : "fx:id=\"cbFilterByProd\" was not injected: check your FXML file 'Products.fxml'.";
        assert tblProdSupp != null : "fx:id=\"tblProdSupp\" was not injected: check your FXML file 'Products.fxml'.";
        assert btnClearFilter != null : "fx:id=\"btnClearFilter\" was not injected: check your FXML file 'Products.fxml'.";
        assert cbFilterBySupp != null : "fx:id=\"cbFilterBySupp\" was not injected: check your FXML file 'Products.fxml'.";
        assert lblError != null : "fx:id=\"lblError\" was not injected: check your FXML file 'Products.fxml'.";
        assert btnCancel != null : "fx:id=\"btnCancel\" was not injected: check your FXML file 'Products.fxml'.";
        assert btnDelete != null : "fx:id=\"btnDelete\" was not injected: check your FXML file 'Products.fxml'.";

        btnSave.setDisable(true);
        //Handles button clicks
        btnExit.setOnMouseClicked(mouseEvent -> System.exit(0));
        btnPrint.setOnMouseClicked(event -> GetPrintScene());
        btnOptions.setOnMouseClicked(event -> GetOptionsScene());
        btnLogin.setOnMouseClicked(event -> GetLoginsScene());
        btnHome.setOnMouseClicked(event -> GetHomeScene());

        btnClearFilter.setOnMouseClicked(event -> { ClearFilters(); });
        btnAdd.setOnMouseClicked(event -> { AddProduct(); });
        btnModify.setOnMouseClicked(event -> { ModifyProduct(); });
        btnSave.setOnMouseClicked(event -> { SaveProduct(); });
        btnCancel.setOnMouseClicked(event -> { ResetProduct(); });
        btnDelete.setOnMouseClicked(event -> { DeleteProduct(); });

        GUIMethods.GetDateTime(dateTime);
        DashboardMethods.changeAgentName(lblAgentName);
        //Populates tables and comboboxes
        PopProdCB();
        PopSupCB();
        PopulateProdSuppTable();
        PopulateProductTable();

        //On selection change of Product combobox filters results
        cbFilterByProd.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Product>() {
            @Override
            public void changed(ObservableValue<? extends Product> observableValue, Product product, Product t1) {
                if(t1 != null)
                {
                    tblProdSupp.getItems().clear();
                    tblProdSupp.getColumns().clear();
                    prodNameFilter = " AND p.ProdName = '" + t1.getProdName() + "' ";
                    PopulateProdSuppTable();
                }
            }
        });

        //On selection change of supplier combobox filters results
        cbFilterBySupp.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Supplier>() {
            @Override
            public void changed(ObservableValue<? extends Supplier> observableValue, Supplier supplier, Supplier t1) {
                if(t1 != null)
                {
                    tblProdSupp.getItems().clear();
                    tblProdSupp.getColumns().clear();
                    suppNameFilter = " AND s.SupName = '" + t1.getSupName() + "' ";
                    PopulateProdSuppTable();
                }
            }
        });

        //On selection of tableview item
        tblProducts.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelction) -> {
            if (newSelction != null)
            {
                TablePosition pos = tblProducts.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();
                ObservableList<String> cellValue = tblProducts.getItems().get(row);
                System.out.println(cellValue);
                tfProductId.setText(cellValue.get(0));
                tfProductName.setText(cellValue.get(1));
            }
        });
    }



    //Dashboard navigation method calls
    private void GetOptionsScene() { DashboardMethods.IconGetScene("SystemDiagnostics.fxml", btnOptions); }
    private void GetLoginsScene() { DashboardMethods.IconGetScene("Login.fxml", btnLogin); }
    private void GetHomeScene() { DashboardMethods.IconGetScene("Home.fxml", btnHome); }
    private void GetPrintScene() { DashboardMethods.IconGetScene("PrintTable.fxml", btnPrint); }

    //Prepares an entry to be added to the database
    private void AddProduct()
    {
        System.out.println("Add Button Clicked");
        isAdd = true;
        lblError.setText("");
        tfProductId.clear();
        tfProductName.clear();
        tfProductId.setEditable(false);
        tfProductName.setEditable(true);
        int lastRow = tblProducts.getItems().size() - 1;
        ObservableList<String> cellValue = tblProducts.getItems().get(lastRow);
        int newProdId = Integer.parseInt(cellValue.get(0)) + 1;
        tfProductId.setText(String.valueOf(newProdId));
        tfProductName.requestFocus();
        btnModify.setDisable(true);
        btnSave.setDisable(false);
    }

    //Prepares an entry to be modified and updated to the database
    private void ModifyProduct()
    {
        System.out.println("Modify Button Clicked");
        isAdd = false;
        lblError.setText("");
        if(tfProductId.getText().equals("") || tfProductName.getText().equals(""))
        {
            System.out.println("Modify Error");
            lblError.setText("Please make a selection from the table before attempting modification");
        }
        else
        {
            currentProdId = Integer.parseInt(tfProductId.getText());
            tfProductName.setEditable(true);
            btnAdd.setDisable(true);
            btnSave.setDisable(false);
        }
    }

    //Deletes an entry in the database
    private void DeleteProduct()
    {
        System.out.println("Delete button clicked");
        lblError.setText("");
        if(tfProductId.getText().equals("") || tfProductName.getText().equals(""))
        {
            System.out.println("Delete Error");
            lblError.setText("Please make a selection from the table before attempting deletion");
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Deletion Conformation");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete entry " + tfProductName.getText() + " from the database?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK)
            {
                // ... user chose OK
                try
                {
                    System.out.println("Deleting entry from database.");
                    Connection conn = DAO.getConnection();
                    @SuppressWarnings("SqlResolve") String sql = "Delete From `products` Where ProductId = ?";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                        stmt.setInt(1, Integer.parseInt(tfProductId.getText()));
                    int rowsInserted = stmt.executeUpdate();
                    if(rowsInserted > 0)
                    {
                        System.out.println("Deletion Successful!");
                        Alert alertSuccess = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Successful Deletion");
                        alert.setHeaderText(null);
                        alert.setContentText("Deletion was successful for product: " + tfProductName.getText());
                        alert.showAndWait();
                    }
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                System.out.println("Deletion canceled");
                ResetProduct();
                tblProducts.getItems().clear();
                tblProducts.getColumns().clear();
            }
            ResetProduct();
            tblProducts.getItems().clear();
            tblProducts.getColumns().clear();
            PopulateProductTable();
        }
    }

    //Saves the new or updated product to the database.
    private void SaveProduct()
    {
        System.out.println("Save Button Clicked");
        System.out.println(tfProductId.getText() + "" + tfProductName.getText());
        if(isAdd && !tfProductId.getText().equals("") && !tfProductName.getText().equals(""))
        {
            System.out.println("Adding entry to database");
            //Insert to database
            try
            {
                Connection conn = DAO.getConnection();
                @SuppressWarnings("SqlResolve") String sql = "Insert Into `Products`(`ProductId`, `ProdName`) Values (?, ?)";
                System.out.println(tfProductId.getText() + "" + tfProductName.getText());
                PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setInt(1, Integer.parseInt(tfProductId.getText()));
                    stmt.setString(2, tfProductName.getText());

                int rowsInserted = stmt.executeUpdate();
                if(rowsInserted > 0)
                {
                    System.out.println("Insert Successful!");
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Update Confirmation");
                    alert.setHeaderText(null);
                    alert.setContentText("Insert was successful for product: " + tfProductName.getText());
                    alert.showAndWait();
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }

        }
        else if(!isAdd && tfProductId.getText() != null && tfProductName.getText() != null)
        {
            System.out.println("Editing entry in database");
            //Update to database
            try
            {
                Connection conn = DAO.getConnection();
                @SuppressWarnings("SqlResolve") String sql = "Update `Products` Set `ProductId` = ?,`ProdName` = ? Where `ProductId` = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, Integer.parseInt(tfProductId.getText()));
                stmt.setString(2, tfProductName.getText());
                stmt.setInt(3, currentProdId);
                int rowsInserted = stmt.executeUpdate();
                if(rowsInserted > 0)
                {
                    System.out.println("Edit Successful!");
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Update Confirmation");
                    alert.setHeaderText(null);
                    alert.setContentText("Edit was successful for product: " + tfProductName.getText());
                    alert.showAndWait();
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("Invalid data. Update unsuccessful. ");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Update Error");
            alert.setHeaderText(null);
            if(isAdd)
            {
                alert.setContentText("Cannot add entry to database due to invalid data. Please try again.");
            }
            else
            {
                alert.setContentText("Cannot edit entry to database due to invalid data. Please try again.");
            }

            alert.showAndWait();
        }
        ResetProduct();
        tblProducts.getItems().clear();
        tblProducts.getColumns().clear();
        PopulateProductTable();
    }

    private void ResetProduct()
    {
        lblError.setText("");
        tfProductId.setEditable(false);
        tfProductName.setEditable(false);
        tfProductId.clear();
        tfProductName.clear();
        btnSave.setDisable(true);
        btnAdd.setDisable(false);
        btnModify.setDisable(false);
    }
    //Populates Product Table
    private void PopulateProductTable()
    {
        if (tblProducts.getItems().isEmpty()) {
            data = FXCollections.observableArrayList();
            try {
                Connection conn = DAO.getConnection();
                String dataQuery = "select * from Products";
                ResultSet tableValues = conn.createStatement().executeQuery(dataQuery);

                for (int i = 0; i < tableValues.getMetaData().getColumnCount(); i++) {
                    final int j = i;
                    TableColumn<ObservableList<String>, String> tableColumn = new TableColumn<>(tableValues.getMetaData().getColumnName(i + 1));
                    tableColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(j)));
                    tblProducts.getColumns().add(tableColumn);
                }
                while (tableValues.next()) {
                    ObservableList<String> row = FXCollections.observableArrayList();
                    for (int i = 1; i <= tableValues.getMetaData().getColumnCount(); i++) {
                        row.add(tableValues.getString(i));
                    }
                    data.add(row);
                }
                tblProducts.getItems().addAll(data);
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //Populates Product_Supplier table
    private void PopulateProdSuppTable()
    {
        if (tblProdSupp.getItems().isEmpty()) {
            data = FXCollections.observableArrayList();
            try {
                Connection conn = DAO.getConnection();
                String dataQuery = "SELECT p.ProdName, s.SupName, ProductSupplierId, p.ProductId, s.SupplierId FROM Products_Suppliers\n" +
                        "\tINNER JOIN Products p ON products_suppliers.ProductId = p.ProductId \n" +
                        "\tINNER JOIN Suppliers s ON products_suppliers.SupplierId = s.SupplierId \n" +
                        "    WHERE p.ProductId > 0" + prodNameFilter + suppNameFilter + "\n" +
                        "    ORDER BY p.ProdName, s.SupName;";
                ResultSet tableValues = conn.createStatement().executeQuery(dataQuery);

                for (int i = 0; i < tableValues.getMetaData().getColumnCount(); i++) {
                    final int j = i;
                    TableColumn<ObservableList<String>, String> tableColumn = new TableColumn<>(tableValues.getMetaData().getColumnName(i + 1));
                    tableColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(j)));
                    tblProdSupp.getColumns().add(tableColumn);
                }
                while (tableValues.next()) {
                    ObservableList<String> row = FXCollections.observableArrayList();
                    for (int i = 1; i <= tableValues.getMetaData().getColumnCount(); i++) {
                        row.add(tableValues.getString(i));
                    }
                    data.add(row);
                }
                tblProdSupp.getItems().addAll(data);
                conn.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    //Redirects user to home
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

    //Populates Product Combobox
    private void PopProdCB()
    {
        try
        {
            Connection conn = DAO.getConnection();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("select * from Products");
            ObservableList<Product> list = FXCollections.observableArrayList();

            while(rs.next())
            {
                list.add(new Product(
                    rs.getInt("productId"),
                    rs.getString("prodName")
                ));
            }
            cbFilterByProd.setItems(list);
            conn.close();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }

    //Populates Supplier Combobox
    private void PopSupCB()
    {
        try
        {
            Connection conn = DAO.getConnection();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("select * from Suppliers");
            ObservableList<Supplier> list = FXCollections.observableArrayList();
            while(rs.next())
            {
                list.add(new Supplier(
                        rs.getInt("supplierId"),
                        rs.getString("supName")
                ));
            }
            cbFilterBySupp.setItems(list);
            conn.close();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }

    //Clears combobox filters
    private void ClearFilters()
    {
        suppNameFilter = "";
        prodNameFilter = "";
        cbFilterByProd.getSelectionModel().clearSelection();
        cbFilterBySupp.getSelectionModel().clearSelection();
        tblProdSupp.getItems().clear();
        tblProdSupp.getColumns().clear();
        PopulateProdSuppTable();
    }
}