/**
 * Sample Skeleton for 'Supplier.fxml' Controller Class
 */

package sample;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ControllerSupplier {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnPrint"
    private FontAwesomeIcon btnPrint; // Value injected by FXMLLoader

    @FXML // fx:id="btnOptions"
    private FontAwesomeIcon btnOptions; // Value injected by FXMLLoader

    @FXML // fx:id="btnUser"
    private FontAwesomeIcon btnUser; // Value injected by FXMLLoader

    @FXML // fx:id="dateTime"
    private Label dateTime; // Value injected by FXMLLoader

    @FXML // fx:id="cbSupCon"
    private ComboBox<SupplierContact> cbSupCon; // Value injected by FXMLLoader

    @FXML // fx:id="tfSupConFirstName"
    private TextField tfSupConFirstName; // Value injected by FXMLLoader

    @FXML // fx:id="tfSupConLastName"
    private TextField tfSupConLastName; // Value injected by FXMLLoader

    @FXML // fx:id="tfSupConCompany"
    private TextField tfSupConCompany; // Value injected by FXMLLoader

    @FXML // fx:id="tfSupConAddress"
    private TextField tfSupConAddress; // Value injected by FXMLLoader

    @FXML // fx:id="tfSupConCity"
    private TextField tfSupConCity; // Value injected by FXMLLoader

    @FXML // fx:id="tfSupConProv"
    private TextField tfSupConProv; // Value injected by FXMLLoader

    @FXML // fx:id="tfSupConPostal"
    private TextField tfSupConPostal; // Value injected by FXMLLoader

    @FXML // fx:id="tfSupConCountry"
    private TextField tfSupConCountry; // Value injected by FXMLLoader

    @FXML // fx:id="tfSupConBusPhone"
    private TextField tfSupConBusPhone; // Value injected by FXMLLoader

    @FXML // fx:id="tfSupConFax"
    private TextField tfSupConFax; // Value injected by FXMLLoader

    @FXML // fx:id="tfSupConEmail"
    private TextField tfSupConEmail; // Value injected by FXMLLoader

    @FXML // fx:id="tfSupConUrl"
    private TextField tfSupConUrl; // Value injected by FXMLLoader

    @FXML // fx:id="tfAffiliationId"
    private TextField tfAffiliationId; // Value injected by FXMLLoader

    @FXML // fx:id="tfSupName"
    private TextField tfSupName; // Value injected by FXMLLoader

    @FXML // fx:id="btnSave"
    private Button btnSave; // Value injected by FXMLLoader

    @FXML // fx:id="btnEdit"
    private Button btnEdit; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnPrint != null : "fx:id=\"btnPrint\" was not injected: check your FXML file 'Supplier.fxml'.";
        assert btnOptions != null : "fx:id=\"btnOptions\" was not injected: check your FXML file 'Supplier.fxml'.";
        assert btnUser != null : "fx:id=\"btnUser\" was not injected: check your FXML file 'Supplier.fxml'.";
        assert dateTime != null : "fx:id=\"dateTime\" was not injected: check your FXML file 'Supplier.fxml'.";
        assert cbSupCon != null : "fx:id=\"cbSupCon\" was not injected: check your FXML file 'Supplier.fxml'.";
        assert tfSupConFirstName != null : "fx:id=\"tfSupConFirstName\" was not injected: check your FXML file 'Supplier.fxml'.";
        assert tfSupConLastName != null : "fx:id=\"tfSupConLastName\" was not injected: check your FXML file 'Supplier.fxml'.";
        assert tfSupConCompany != null : "fx:id=\"tfSupConCompany\" was not injected: check your FXML file 'Supplier.fxml'.";
        assert tfSupConAddress != null : "fx:id=\"tfSupConAddress\" was not injected: check your FXML file 'Supplier.fxml'.";
        assert tfSupConCity != null : "fx:id=\"tfSupConCity\" was not injected: check your FXML file 'Supplier.fxml'.";
        assert tfSupConProv != null : "fx:id=\"tfSupConProv\" was not injected: check your FXML file 'Supplier.fxml'.";
        assert tfSupConPostal != null : "fx:id=\"tfSupConPostal\" was not injected: check your FXML file 'Supplier.fxml'.";
        assert tfSupConCountry != null : "fx:id=\"tfSupConCountry\" was not injected: check your FXML file 'Supplier.fxml'.";
        assert tfSupConBusPhone != null : "fx:id=\"tfSupConBusPhone\" was not injected: check your FXML file 'Supplier.fxml'.";
        assert tfSupConFax != null : "fx:id=\"tfSupConFax\" was not injected: check your FXML file 'Supplier.fxml'.";
        assert tfSupConEmail != null : "fx:id=\"tfSupConEmail\" was not injected: check your FXML file 'Supplier.fxml'.";
        assert tfSupConUrl != null : "fx:id=\"tfSupConUrl\" was not injected: check your FXML file 'Supplier.fxml'.";
        assert tfAffiliationId != null : "fx:id=\"tfAffiliationId\" was not injected: check your FXML file 'Supplier.fxml'.";
        assert tfSupName != null : "fx:id=\"tfSupName\" was not injected: check your FXML file 'Supplier.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'Supplier.fxml'.";
        assert btnEdit != null : "fx:id=\"btnEdit\" was not injected: check your FXML file 'Supplier.fxml'.";

        try {
            Connection conn = getConnetion();
            Statement stmt = conn.createStatement();
            @SuppressWarnings("SqlResolve") ResultSet rs = stmt.executeQuery("select SupplierContactId, SupConFirstName, SupConLastName, " +
                    "SupConCompany, SupConAddress, " +
                    "SupConCity, SupConProv, SupConPostal, SupConCountry, SupConBusPhone, SupConFax, SupConEmail, " +
                    "SupConURL, AffiliationId, SupplierContacts.SupplierId, SupName " +
                    "from SupplierContacts join Suppliers " +
                    "on SupplierContacts.SupplierId = Suppliers.SupplierId");
            ObservableList<SupplierContact> list = FXCollections.observableArrayList();
            while (rs.next())
            {
                list.add(new SupplierContact(rs.getInt("SupplierContactId"),
                                            rs.getString("SupConFirstName"),
                                            rs.getString("SupConLastName"),
                                            rs.getString("SupConCompany"),
                                            rs.getString("SupConAddress"),
                                            rs.getString("SupConCity"),
                                            rs.getString("SupConProv"),
                                            rs.getString("SupConPostal"),
                                            rs.getString("SupConCountry"),
                                            rs.getString("SupConBusPhone"),
                                            rs.getString("SupConFax"),
                                            rs.getString("SupConEmail"),
                                            rs.getString("SupConURL"),
                                            rs.getString("AffiliationId"),
                                            rs.getInt("SupplierId"),
                                            rs.getString("SupName")
                        ));
            }
            cbSupCon.setItems(list);
            conn.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        cbSupCon.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SupplierContact>() {
            @Override
            public void changed(ObservableValue<? extends SupplierContact> observableValue, SupplierContact oldValue,
                                SupplierContact newValue) {
                tfSupConFirstName.setText(newValue.getSupConFirstName());
                tfSupConLastName.setText(newValue.getSupConLastName());
                tfSupConCompany.setText(newValue.getSupConCompany());
                tfSupConAddress.setText(newValue.getSupConAddress());
                tfSupConCity.setText(newValue.getSupConCity());
                tfSupConProv.setText(newValue.getSupConProv());
                tfSupConPostal.setText(newValue.getSupConPostal());
                tfSupConCountry.setText(newValue.getSupConCountry());
                tfSupConBusPhone.setText(newValue.getSupConBusPhone());
                tfSupConFax.setText(newValue.getSupConFax());
                tfSupConEmail.setText(newValue.getSupConEmail());
                tfSupConUrl.setText(newValue.getSupConUrl());
                tfAffiliationId.setText(newValue.getAffiliationId());
                tfSupName.setText(newValue.getSupName());

            }
        });

        btnSave.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String sql = "UPDATE `suppliercontacts` SET " +
                        "`SupConFirstName`=?,`SupConLastName`=?,`SupConCompany`=?," +
                        "`SupConAddress`=?,`SupConCity`=?,`SupConProv`=?,`SupConPostal`=?," +
                        "`SupConCountry`=?,`SupConBusPhone`=?,`SupConFax`=?," +
                        "`SupConEmail`=?,`SupConURL`=?,`AffiliationId`=?,`SupplierId`=? " +
                        "WHERE SupplierContactId=?";

                try {
                    Connection conn = getConnetion();
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, tfSupConFirstName.getText());
                    pstmt.setString(2, tfSupConLastName.getText());
                    pstmt.setString(3, tfSupConCompany.getText());
                    pstmt.setString(4, tfSupConAddress.getText());
                    pstmt.setString(5, tfSupConCity.getText());
                    pstmt.setString(6, tfSupConProv.getText());
                    pstmt.setString(7, tfSupConPostal.getText());
                    pstmt.setString(8, tfSupConCountry.getText());
                    pstmt.setString(9, tfSupConBusPhone.getText());
                    pstmt.setString(10, tfSupConFax.getText());
                    pstmt.setString(11, tfSupConEmail.getText());
                    pstmt.setString(12, tfSupConUrl.getText());
                    pstmt.setString(13, tfAffiliationId.getText());

                    }
                catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

    }

    private Connection getConnetion() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "root", "");
    }
}

