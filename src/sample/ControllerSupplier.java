/**
 * Sample Skeleton for 'Supplier.fxml' Controller Class
 */

package sample;

/*
    Supplier Controller Class
    Handles all of the data from SupplierContacts table
    Code by Jin Meng
 */
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import objects.GUIMethods;

import java.net.URL;
import java.sql.*;
import java.util.*;

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

    @FXML // fx:id="btnEditFA"
    private FontAwesomeIcon btnEditFA; // Value injected by FXMLLoader

    @FXML // fx:id="btnSaveFA"
    private FontAwesomeIcon btnSaveFA; // Value injected by FXMLLoader

    @FXML // fx:id="btnAddFA"
    private FontAwesomeIcon btnAddFA; // Value injected by FXMLLoader
    @FXML
    private FontAwesomeIcon btnLogin;

    @FXML // fx:id="btnDeleteFA"
    private FontAwesomeIcon btnDeleteFA; // Value injected by FXMLLoader
    @FXML
    private FontAwesomeIcon btnExit;

    @FXML
    private FontAwesomeIcon btnHome;

    @FXML
    private Label lblAgentName;

    @FXML
    private TextField tfSupId;

    private boolean addMode;
    protected int classSupplierContactId;

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
        assert tfSupId != null : "fx:id=\"tfSupId\" was not injected: check your FXML file 'Supplier.fxml'.";
        assert tfSupName != null : "fx:id=\"tfSupName\" was not injected: check your FXML file 'Supplier.fxml'.";
        assert btnEditFA != null : "fx:id=\"btnEditFA\" was not injected: check your FXML file 'Supplier.fxml'.";
        assert btnSaveFA != null : "fx:id=\"btnSaveFA\" was not injected: check your FXML file 'Supplier.fxml'.";
        assert btnAddFA != null : "fx:id=\"btnAddFA\" was not injected: check your FXML file 'Supplier.fxml'.";
        assert btnDeleteFA != null : "fx:id=\"btnDeleteFA\" was not injected: check your FXML file 'Supplier.fxml'.";
        assert btnLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'Supplier.fxml'.";
        assert btnExit != null : "fx:id=\"btnExit\" was not injected: check your FXML file 'Supplier.fxml'.";
        assert btnHome != null : "fx:id=\"btnHome\" was not injected: check your FXML file 'Supplier.fxml'.";
        assert lblAgentName != null : "fx:id=\"lblAgentName\" was not injected: check your FXML file 'Supplier.fxml'.";

        btnExit.setOnMouseClicked(mouseEvent -> System.exit(0));
        btnPrint.setOnMouseClicked(event -> GetPrintScene());
        btnOptions.setOnMouseClicked(event -> GetOptionsScene());
        btnLogin.setOnMouseClicked(event -> GetLoginsScene());
        btnHome.setOnMouseClicked(event -> GetHomeScene());

        btnExit.setOnMouseClicked(event -> System.exit(0));

        tfSupName.setEditable(false);

        setTfEditable(false);

        loadCbSupCon();


// set up four buttons, make buttons editable only when they are needed
        btnEditFA.setOnMouseClicked(event -> {
            setTfEditable(true);
            addMode = false;
        });



        btnAddFA.setOnMouseClicked(event -> {
            setTfEditable(true);
            tfClear();
            addMode = true;
        });


/* By switching a boolean varible addMode between true or false, the user have control of creating a new supplier
 contact, or update an existing one
 */
        btnSaveFA.setOnMouseClicked(event -> {
            if (fieldValidator() && affiliIdValidator() && supIdValidator())
            {
                if (addMode) {
                    @SuppressWarnings("SqlResolve") String sql = "INSERT INTO suppliercontacts (SupConFirstName, " +
                            "SupConLastName, SupConCompany, SupConAddress, SupConCity, SupConProv, " +
                            "SupConPostal, SupConCountry, SupConBusPhone, SupConFax, SupConEmail, SupConURL, " +
                            "AffiliationId, SupplierId) VALUES (?,?,?,?,?,?,?," +
                            "?,?,?,?,?,?,?)";

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
                        pstmt.setInt(14, Integer.parseInt(tfSupId.getText()));

                        int rowsAffected = pstmt.executeUpdate();
                        if (rowsAffected > 0) {
                            tfClear();
                        } else {
                            System.out.println("update failed");
                        }

                        conn.close();

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    loadCbSupCon();
                } else {
                    @SuppressWarnings("SqlResolve") String sql1 = "UPDATE `SupplierContacts` SET " +
                            "`SupConFirstName`=?,`SupConLastName`=?,`SupConCompany`=?," +
                            "`SupConAddress`=?,`SupConCity`=?,`SupConProv`=?,`SupConPostal`=?," +
                            "`SupConCountry`=?,`SupConBusPhone`=?,`SupConFax`=?," +
                            "`SupConEmail`=?,`SupConURL`=?,`AffiliationId`=?,`SupplierId`=? " +
                            "WHERE SupplierContactId=?";

                    try {
                        Connection conn = getConnetion();
                        PreparedStatement pstmt1 = conn.prepareStatement(sql1);
                        pstmt1.setString(1, tfSupConFirstName.getText());
                        pstmt1.setString(2, tfSupConLastName.getText());
                        pstmt1.setString(3, tfSupConCompany.getText());
                        pstmt1.setString(4, tfSupConAddress.getText());
                        pstmt1.setString(5, tfSupConCity.getText());
                        pstmt1.setString(6, tfSupConProv.getText());
                        pstmt1.setString(7, tfSupConPostal.getText());
                        pstmt1.setString(8, tfSupConCountry.getText());
                        pstmt1.setString(9, tfSupConBusPhone.getText());
                        pstmt1.setString(10, tfSupConFax.getText());
                        pstmt1.setString(11, tfSupConEmail.getText());
                        pstmt1.setString(12, tfSupConUrl.getText());
                        pstmt1.setString(13, tfAffiliationId.getText());
                        pstmt1.setString(14, tfSupId.getText());
                        pstmt1.setString(15, String.valueOf(classSupplierContactId));


                        int rowsAffected = pstmt1.executeUpdate();
                        if (rowsAffected > 0) {
                            tfClear();
                        } else {
                            System.out.println("update failed");
                        }

                        conn.close();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    loadCbSupCon();
                    setTfEditable(false);
                }
            }
        });


        // the Delete button can delete a record in the database
        btnDeleteFA.setOnMouseClicked(event -> {
            @SuppressWarnings("SqlResolve") String sql = "DELETE FROM `suppliercontacts` WHERE SupplierContactId=?";
        DashboardMethods.changeAgentName(lblAgentName);
        GUIMethods.GetDateTime(dateTime);

            try {
                Connection conn = getConnetion();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, String.valueOf(classSupplierContactId));

                int rowsAffected = pstmt.executeUpdate();
                if(rowsAffected > 0)
                {
                    tfClear();
                }
                else {
                    System.out.println("update failed");
                }
                conn.close();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            loadCbSupCon();
        });


    }

    // load all existing supplier contacts from the database when the scene is loaded
    protected void loadCbSupCon(){
        try {
            Connection conn = getConnetion();
            Statement stmt = conn.createStatement();
            @SuppressWarnings("SqlResolve") ResultSet rs = stmt.executeQuery("select SupplierContactId, SupConFirstName, SupConLastName, " +
                    "SupConCompany, SupConAddress, " +
                    "SupConCity, SupConProv, SupConPostal, SupConCountry, SupConBusPhone, SupConFax, SupConEmail, " +
                    "SupConURL, AffiliationId, SupplierContacts.SupplierId, SupName " +
                    "from SupplierContacts join Suppliers " +
                    "on SupplierContacts.SupplierId = Suppliers.SupplierId");

            Comparator<SupplierContact> scComparator = Comparator.comparing(SupplierContact::getSupConCompany);
            ObservableList<SupplierContact> list = FXCollections.observableArrayList();
            while (rs.next()) {
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
            Collections.sort(list, scComparator);
            cbSupCon.setItems(list);
            conn.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // get selection mode uses a listener to listen to the selection of the ComboBox
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
                tfSupId.setText(String.valueOf(newValue.getSupplierId()));
                tfSupName.setText(newValue.getSupName());


                classSupplierContactId = newValue.getSupplierContactId();

                System.out.println("SupplierContactId = " + classSupplierContactId);

            }
        });
    }

    private Connection getConnetion() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "root", "");
    }

    private void setTfEditable(boolean b){
        tfSupConFirstName.setEditable(b);
        tfSupConLastName.setEditable(b);
        tfSupConCompany.setEditable(b);
        tfSupConAddress.setEditable(b);
        tfSupConCity.setEditable(b);
        tfSupConProv.setEditable(b);
        tfSupConPostal.setEditable(b);
        tfSupConCountry.setEditable(b);
        tfSupConBusPhone.setEditable(b);
        tfSupConFax.setEditable(b);
        tfSupConEmail.setEditable(b);
        tfSupConUrl.setEditable(b);
        tfAffiliationId.setEditable(b);
        tfSupId.setEditable(b);
    }

    private void tfClear(){
        tfSupConFirstName.clear();
        tfSupConLastName.clear();
        tfSupConCompany.clear();
        tfSupConAddress.clear();
        tfSupConCity.clear();
        tfSupConProv.clear();
        tfSupConPostal.clear();
        tfSupConCountry.clear();
        tfSupConBusPhone.clear();
        tfSupConFax.clear();
        tfSupConEmail.clear();
        tfSupConUrl.clear();
        tfAffiliationId.clear();
        tfSupId.clear();
        tfSupName.clear();
    }

    // validate that all required fields are filled
    private boolean fieldValidator(){
        boolean isValid = false;
        if (tfSupConCompany.getText().isEmpty() || tfSupId.getText().isEmpty() )
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Empty Fields");
            alert.setContentText("You need to fill up Company Name Affiliation Id and Supplier Id");
            alert.showAndWait();
        }
        else
            isValid = true;
        return isValid;
    }

    // validate that affiliation id entered matches records in affiliations table
    private boolean affiliIdValidator() {
        boolean isValid = false;
        ArrayList<String> affiliIDs = new ArrayList<>();
        affiliIDs.add("ACTA");
        affiliIDs.add("ACTANEW");
        affiliIDs.add("ACTANEWP");
        affiliIDs.add("ACTAPGY");
        affiliIDs.add("NEW");
        affiliIDs.add("NEWPGY");
        affiliIDs.add("PGY");
        if (tfAffiliationId.getText() == null) {
            isValid = true;
        }else
        {
            for (int i = 0; i < affiliIDs.size(); i++) {
                if (tfAffiliationId.getText().equals(affiliIDs.get(i)))
                    isValid = true;
            }
        }
        if (isValid == false){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid ID");
            alert.setContentText("The Affiliation ID is invalid, please confirm it with you agent.");
            alert.showAndWait();
        }
        return isValid;
    }

    // validate that supplier id entered matches records in suppliers table
    private boolean supIdValidator(){
        boolean isValid = false;
        List<Integer> supIDs = Arrays.asList(69, 80, 100, 317, 323, 796, 828, 845, 908, 1005, 1028, 1040, 1205, 1406,
                1416, 1425, 1542, 1620, 1634, 1685, 1713, 1766, 1859, 1918, 2140, 2386, 2466, 2588, 2592, 2827, 2938,
                2987, 2998, 3119, 3212, 3273, 3376, 3549, 3576, 3589, 3600, 3622, 3633, 3650, 4196, 5081, 5228, 5492,
                5777, 5796, 5827, 5857, 5346, 6346, 6505, 6550, 6873, 7244, 7377, 8089, 8837, 9285, 9323, 9396, 9766,
                9785, 11156, 11160, 11163, 11172, 11174, 11237, 11549, 12657, 13596);

        ArrayList<Integer> supidArrayList = new ArrayList<>();
        supidArrayList.addAll(supIDs);
        for (int i=0; i < supidArrayList.size(); i++) {
            if (Integer.parseInt(tfSupId.getText()) == supidArrayList.get(i))
                isValid = true;
        }
        if (isValid == false)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid ID");
                alert.setContentText("The Supplier ID is invalid, please confirm it with you agent.");
                alert.showAndWait();

            }
        return isValid;
    }

    //enable buttons to build links to other scenes
    private void GetOptionsScene() { DashboardMethods.IconGetScene("SystemDiagnostics.fxml", btnOptions); }
    private void GetLoginsScene() { DashboardMethods.IconGetScene("Login.fxml", btnLogin); }
    private void GetHomeScene() { DashboardMethods.IconGetScene("Home.fxml", btnHome); }
    private void GetPrintScene() { DashboardMethods.IconGetScene("PrintTable.fxml", btnPrint); }
}

