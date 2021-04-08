package sample;

import database.DAO;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import objects.GUIMethods;
import objects.Packages;
import objects.PackagesProductsSuppliers;
import objects.ProductsSuppliers;

import java.sql.*;

public class ControllerPackages {
    private ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();

    @FXML
    private Label datetime;

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
    private FontAwesomeIcon btnExit;

    @FXML
    private VBox vbLeft;

    @FXML
    private Label lblPackages;

    @FXML
    private ComboBox<Packages> cbPkg;

    @FXML
    private Label lblPkgName;

    @FXML
    private TextField tfPkgName;

    @FXML
    private Label lblDescription;

    @FXML
    private TextArea taDescription;

    @FXML
    private Label lblStartDate;

    @FXML
    private DatePicker dpStartDate;

    @FXML
    private Label lblEndDate;

    @FXML
    private DatePicker dpEndDate;

    @FXML
    private Label lblPrice;

    @FXML
    private TextField tfPrice;

    @FXML
    private Label lblCommission;

    @FXML
    private TextField tfCommission;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnPkgDel;

    @FXML
    private Button btnPPSDel;

    @FXML
    private ComboBox<PackagesProductsSuppliers> cbPackProdSup;

    @FXML
    private TableView<ObservableList<String>> tblProducts;

    @FXML
    private Button btnEditPPS;

    @FXML
    private TextField tfPPSID;

    @FXML
    private TextField tfPackId;

    @FXML
    private Button btnPPSUpdate;

    @FXML
    private ChoiceBox<Packages> cbPPSPkgID;

    @FXML
    private ChoiceBox<ProductsSuppliers> cbPPSID;

    @FXML
    private Button btnPPSAdd;

    @FXML
    private Button btnPkgAdd;

    @FXML
    private Button btnCancel;

    @FXML
    private Label lblAgentName;

    @FXML
    void initialize() {
        assert lblAgentName != null : "fx:id=\"lblAgentName\" was not injected: check your FXML file 'Packages.fxml'.";
        assert datetime != null : "fx:id=\"datetime\" was not injected: check your FXML file 'Packages.fxml'.";
        assert btnPrint != null : "fx:id=\"btnPrint\" was not injected: check your FXML file 'Packages.fxml'.";
        assert btnOptions != null : "fx:id=\"btnOptions\" was not injected: check your FXML file 'Packages.fxml'.";
        assert btnHome != null : "fx:id=\"btnHome\" was not injected: check your FXML file 'Packages.fxml'.";
        assert btnLogin != null : "fx:id=\"btnAgentLogin\" was not injected: check your FXML file 'Packages.fxml'.";
        assert btnUser != null : "fx:id=\"btnUser\" was not injected: check your FXML file 'Packages.fxml'.";
        assert btnExit != null : "fx:id=\"btnExit\" was not injected: check your FXML file 'Packages.fxml'.";
        assert vbLeft != null : "fx:id=\"vbLeft\" was not injected: check your FXML file 'Packages.fxml'.";
        assert lblPackages != null : "fx:id=\"lblPackages\" was not injected: check your FXML file 'Packages.fxml'.";
        assert cbPkg != null : "fx:id=\"cbPkg\" was not injected: check your FXML file 'Packages.fxml'.";
        assert lblPkgName != null : "fx:id=\"lblPkgName\" was not injected: check your FXML file 'Packages.fxml'.";
        assert tfPkgName != null : "fx:id=\"tfPkgName\" was not injected: check your FXML file 'Packages.fxml'.";
        assert lblDescription != null : "fx:id=\"lblDescription\" was not injected: check your FXML file 'Packages.fxml'.";
        assert taDescription != null : "fx:id=\"taDescription\" was not injected: check your FXML file 'Packages.fxml'.";
        assert lblStartDate != null : "fx:id=\"lblStartDate\" was not injected: check your FXML file 'Packages.fxml'.";
        assert dpStartDate != null : "fx:id=\"dpStartDate\" was not injected: check your FXML file 'Packages.fxml'.";
        assert lblEndDate != null : "fx:id=\"lblEndDate\" was not injected: check your FXML file 'Packages.fxml'.";
        assert dpEndDate != null : "fx:id=\"dpEndDate\" was not injected: check your FXML file 'Packages.fxml'.";
        assert lblPrice != null : "fx:id=\"lblPrice\" was not injected: check your FXML file 'Packages.fxml'.";
        assert tfPrice != null : "fx:id=\"tfPrice\" was not injected: check your FXML file 'Packages.fxml'.";
        assert lblCommission != null : "fx:id=\"lblCommission\" was not injected: check your FXML file 'Packages.fxml'.";
        assert tfCommission != null : "fx:id=\"tfCommission\" was not injected: check your FXML file 'Packages.fxml'.";
        assert btnEdit != null : "fx:id=\"btnEdit\" was not injected: check your FXML file 'Packages.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'Packages.fxml'.";
        assert cbPackProdSup != null : "fx:id=\"cbPackProdSup\" was not injected: check your FXML file 'Packages.fxml'.";
        assert tblProducts != null : "fx:id=\"tblProducts\" was not injected: check your FXML file 'Packages.fxml'.";
        assert btnEditPPS != null : "fx:id=\"btnEditPPS\" was not injected: check your FXML file 'Packages.fxml'.";
        assert tfPPSID != null : "fx:id=\"tfPPSID\" was not injected: check your FXML file 'Packages.fxml'.";
        assert tfPackId != null : "fx:id=\"tfPackId\" was not injected: check your FXML file 'Packages.fxml'.";
        assert btnPPSUpdate != null : "fx:id=\"btnPPSUpdate\" was not injected: check your FXML file 'Packages.fxml'.";
        assert cbPPSPkgID != null : "fx:id=\"cbPPSPkgID\" was not injected: check your FXML file 'Packages.fxml'.";
        assert cbPPSID != null : "fx:id=\"cbPPSID\" was not injected: check your FXML file 'Packages.fxml'.";
        assert btnPPSAdd != null : "fx:id=\"btnPPSAdd\" was not injected: check your FXML file 'Packages.fxml'.";
        assert btnPkgAdd != null : "fx:id=\"btnPPSAdd\" was not injected: check your FXML file 'Packages.fxml'.";
        assert btnCancel != null : "fx:id=\"btnCancel\" was not injected: check your FXML file 'Packages.fxml'.";
        assert btnPPSDel != null : "fx:id=\"btnPPSDel\" was not injected: check your FXML file 'Packages.fxml'.";
        assert btnPkgDel != null : "fx:id=\"btnPPSDel\" was not injected: check your FXML file 'Packages.fxml'.";
// DASHBOARD BUTTONS ==================================================================================================
        btnExit.setOnMouseClicked(mouseEvent -> System.exit(0));
        btnPrint.setOnMouseClicked(event -> GetPrintScene());
        btnOptions.setOnMouseClicked(event -> GetOptionsScene());
        btnLogin.setOnMouseClicked(event -> GetLoginsScene());
        btnHome.setOnMouseClicked(event -> GetHomeScene());
        btnUser.setOnMouseClicked(event -> GetUserScene());
// DASHBOARD METHODS ====== ============================================================================================
        DashboardMethods.changeAgentName(lblAgentName);
        GUIMethods.GetDateTime(datetime);
//Connect to Database for ComboBox
        try {
            Connection db = DAO.getConnection();
            Statement stat = db.createStatement();
            ResultSet rs = stat.executeQuery("select * from packages p ");

            ObservableList<Packages> pkgs = FXCollections.observableArrayList();
            while (rs.next()) {
                pkgs.add(new Packages(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getTimestamp(3),
                        rs.getTimestamp(4),
                        rs.getString(5),
                        rs.getDouble(6),
                        rs.getDouble(7)));
            }
            cbPkg.setItems(pkgs);
            cbPPSPkgID.setItems(pkgs);
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        cbPkg.getSelectionModel().selectedItemProperty().addListener((observableValue, packages, t1) -> {
            tfPkgName.setText(t1.getPkgName());
            taDescription.setText(t1.getPkgDesc());
            tfPrice.setText(t1.getPkgBasePrice() + "");
            tfCommission.setText(t1.getPkgAgencyCommission() + "");
            dpStartDate.setValue(t1.getPkgStartDate().toLocalDateTime().toLocalDate());
            dpEndDate.setValue(t1.getPkgEndDate().toLocalDateTime().toLocalDate());
            tblProducts.getColumns().addAll();
        });

        tblProducts.getItems().clear();
        tblProducts.getColumns().clear();
        if (tblProducts.getItems().isEmpty()) {
            data = FXCollections.observableArrayList();
            try {
                Connection conn = DAO.getConnection();
                String dataQuery = "select p.PackageId, p.PkgName, s.SupName, prod.ProdName from packages p \n" +
                        "                    join Packages_Products_Suppliers pps on pps.PackageId = p.PackageId \n" +
                        "                    join Products_Suppliers ps on ps.ProductSupplierId = pps.ProductSupplierId \n" +
                        "                    join Products prod on prod.ProductId = ps.ProductId \n" +
                        "                    join Suppliers s on s.SupplierId = ps.SupplierId ";
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

//SAVE BUTTON TO HANDLE PACKAGES

            btnSave.setOnMouseClicked(event -> {
                String sql = "UPDATE `packages` " +
                        "SET `PackageId`=?," +
                        "`PkgName`=?," +
                        "`PkgStartDate`=?," +
                        "`PkgEndDate`=?," +
                        "`PkgDesc`=?," +
                        "`PkgBasePrice`=?," +
                        "`PkgAgencyCommission`=?" +
                        "WHERE PackageId=?";
                try {
                    Connection conn = DAO.getConnection();
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setLong(1, Long.parseLong(String.valueOf(cbPkg.getValue().getPackageId())));
                    stmt.setString(2, tfPkgName.getText());
                    stmt.setDate(3, Date.valueOf(dpStartDate.getValue()));
                    stmt.setDate(4, Date.valueOf(dpEndDate.getValue()));
                    stmt.setString(5, taDescription.getText());
                    stmt.setDouble(6, Double.parseDouble(tfPrice.getText()));
                    stmt.setDouble(7, Double.parseDouble(tfCommission.getText()));
                    stmt.setLong(8, Long.parseLong(String.valueOf(cbPkg.getValue().getPackageId())));

                    int rowsAffected = stmt.executeUpdate();
                    String output = (rowsAffected > 0) ? "Successful" : "Failed";
                    System.out.println(output);

                    conn.close();

                } catch (SQLException e) {
                    System.out.println(e);
                }

                btnSave.setDisable(true);
                btnEdit.setDisable(false);
                tfPkgName.setDisable(true);
                taDescription.setDisable(true);
                tfPrice.setDisable(true);
                tfCommission.setDisable(true);
                dpStartDate.setDisable(true);
                dpEndDate.setDisable(true);
                btnPkgAdd.setDisable(true);
                btnCancel.setDisable(true);
                btnPkgDel.setDisable(true);
            });

//EDIT BUTTON TO HANDLE PACKAGES
            btnEdit.setOnMouseClicked(event -> {
                btnSave.setDisable(false);
                btnEdit.setDisable(true);
                tfPkgName.setDisable(false);
                taDescription.setDisable(false);
                tfPrice.setDisable(false);
                tfCommission.setDisable(false);
                dpStartDate.setDisable(false);
                dpEndDate.setDisable(false);
                btnPkgAdd.setDisable(false);
                btnCancel.setDisable(false);
                btnPkgDel.setDisable(false);
            });

//COMBO BOX FOR PACKAGES/PRODUCTS/SUPPLIERS
            try {
                Connection db = DAO.getConnection();
                Statement stat = db.createStatement();
                ResultSet rs = stat.executeQuery("select p.PackageId, pps.ProductSupplierId, s.SupplierId, " +
                        "p.PkgName, prod.ProdName, s.SupName from packages p " +
                        "join Packages_Products_Suppliers pps on pps.PackageId = p.PackageId " +
                        "join Products_Suppliers ps on ps.ProductSupplierId = pps.ProductSupplierId " +
                        "join Products prod on prod.ProductId = ps.ProductId " +
                        "join Suppliers s on s.SupplierId = ps.SupplierId");

                ObservableList<PackagesProductsSuppliers> pps = FXCollections.observableArrayList();
                while (rs.next()) {
                    pps.add(new PackagesProductsSuppliers(rs.getInt(1), rs.getInt(2), rs.getString(5), rs.getString(6)));
                }

                cbPackProdSup.setItems(pps);
                db.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }


//PRODUCT SUPPLIERS CHOICE BOX
            try {
                Connection db = DAO.getConnection();
                Statement stat = db.createStatement();
                ResultSet rs = stat.executeQuery("select ps.ProductSupplierId, s.SupplierId, prod.ProdName, s.SupName " +
                        "from Products_Suppliers ps " +
                        "join Products prod on prod.ProductId = ps.ProductId " +
                        "join Suppliers s on s.SupplierId = ps.SupplierId");

                ObservableList<ProductsSuppliers> ps = FXCollections.observableArrayList();
                while (rs.next()) {
                    ps.add(new ProductsSuppliers(rs.getInt(1), rs.getString(3), rs.getString(4)));
                }
                cbPPSID.setItems(ps);

                db.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            cbPPSID.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ProductsSuppliers>() {
                @Override
                public void changed(ObservableValue<? extends ProductsSuppliers> observable, ProductsSuppliers oldValue, ProductsSuppliers t1) {
                    tfPPSID.setText(t1.getProductSupplierId() + "");
                }
            });

            cbPPSPkgID.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Packages>() {
                @Override
                public void changed(ObservableValue<? extends Packages> observable, Packages oldValue, Packages t1) {
                    tfPackId.setText(t1.getPackageId() + "");
                }
            });


//SET TEXT BOXES FOR PACKAGE ID AND PRODUCT/SUPPLIER ID
            cbPackProdSup.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PackagesProductsSuppliers>() {
                @Override
                public void changed(ObservableValue<? extends PackagesProductsSuppliers> observableValue,
                                    PackagesProductsSuppliers packages, PackagesProductsSuppliers t1) {
                    tfPackId.setText(t1.getPackageId() + "");
                    tfPPSID.setText(t1.getProductSupplierId() + "");
                }
            });


//EDIT BUTTON TO HANDLE PACKAGES PRODUCTS SUPPLIERS
            btnEditPPS.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    tfPackId.setDisable(false);
                    tfPPSID.setDisable(false);
                    btnEditPPS.setDisable(true);
                    btnPPSUpdate.setDisable(false);
                    btnPPSAdd.setDisable(false);
                    btnCancel.setDisable(false);
                    btnPPSDel.setDisable(false);
                }
            });

//UPDATE PPS DATABASE
            btnPPSUpdate.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    String sql = "UPDATE `packages_products_suppliers` " +
                            "SET `PackageId`=?," +
                            "`ProductSupplierId`=? " +
                            "WHERE PackageId=?";
                    try {
                        Connection conn = DAO.getConnection();
                        PreparedStatement stmt = conn.prepareStatement(sql);
                        stmt.setLong(1, Long.parseLong(tfPackId.getText()));
                        stmt.setLong(2, Long.parseLong(tfPPSID.getText()));
                        stmt.setLong(3, Long.parseLong(tfPackId.getText()));

                        int rowsAffected = stmt.executeUpdate();
                        String output = (rowsAffected > 0) ? "Successful" : "Failed";
                        System.out.println(output);

                        conn.close();

                    } catch (SQLException e) {
                        System.out.println(e);
                    }
                    btnEditPPS.setDisable(false);
                    btnPPSUpdate.setDisable(true);
                    tfPackId.setDisable(true);
                    tfPPSID.setDisable(true);
                    btnPPSDel.setDisable(true);
                }
            });

//ADD TO PPS DATABASE
            btnPPSAdd.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
                        String sql = "INSERT INTO `packages_products_suppliers`(`PackageId`, `ProductSupplierId`) " +
                                "VALUES (" + Long.parseLong(tfPackId.getText()) + "," + Long.parseLong(tfPPSID.getText()) + ")";
                        Connection conn = DAO.getConnection();
                        PreparedStatement stmt = conn.prepareStatement(sql);

                        int rowsAffected = stmt.executeUpdate();
                        String output = (rowsAffected > 0) ? "Successful" : "Failed";
                        System.out.println(output);

                        conn.close();

                    } catch (SQLException e) {
                        System.out.println(e);
                    }

                }
            });

//ADD TO PACKAGES DATABASE
            btnPkgAdd.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    String sql = "INSERT INTO packages (PkgName, PkgStartDate, PkgEndDate, PkgDesc, " +
                            "PkgBasePrice, PkgAgencyCommission) " +
                            "VALUES (? , ?, ?, ?, ?, ?)";
                    Connection conn = DAO.getConnection();
                    try {
                        PreparedStatement stmt = conn.prepareStatement(sql);
                        stmt.setString(1, tfPkgName.getText());
                        stmt.setString(2, String.valueOf(dpStartDate.getValue()));
                        stmt.setString(3, String.valueOf(dpEndDate.getValue()));
                        stmt.setString(4, taDescription.getText());
                        stmt.setString(5, tfPrice.getText());
                        stmt.setString(6, tfCommission.getText());
                        int rowsAffected = stmt.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.println("update worked");
                        } else {
                            System.out.println("update failed");
                        }
                        conn.close();

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    btnSave.setDisable(true);
                    btnEdit.setDisable(false);
                    tfPkgName.setDisable(true);
                    taDescription.setDisable(true);
                    tfPrice.setDisable(true);
                    tfCommission.setDisable(true);
                    dpStartDate.setDisable(true);
                    dpEndDate.setDisable(true);
                    btnPkgAdd.setDisable(true);
                    btnCancel.setDisable(true);
                    btnPkgDel.setDisable(true);
                }
            });

//DELETE FROM PACKAGES
            btnPkgDel.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    String sql = "DELETE FROM packages WHERE PkgName=?";
                    Connection conn = DAO.getConnection();
                    try {
                        PreparedStatement stmt = conn.prepareStatement(sql);
                        stmt.setString(1, tfPkgName.getText());
                        int rowsAffected = stmt.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.println("DELETE worked");
                        } else {
                            System.out.println("DELETE failed");
                        }
                        conn.close();

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    btnSave.setDisable(true);
                    btnEdit.setDisable(false);
                    tfPkgName.setDisable(true);
                    taDescription.setDisable(true);
                    tfPrice.setDisable(true);
                    tfCommission.setDisable(true);
                    dpStartDate.setDisable(true);
                    dpEndDate.setDisable(true);
                    btnPkgAdd.setDisable(true);
                    btnCancel.setDisable(true);
                    btnPkgDel.setDisable(true);
                }
            });

//DELETE FROM PPS
            btnPPSDel.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    String sql = "DELETE FROM packages_products_suppliers WHERE PackageId=? and ProductSupplierId=?";
                    Connection conn = DAO.getConnection();
                    try {
                        PreparedStatement stmt = conn.prepareStatement(sql);
                        stmt.setString(1, tfPackId.getText());
                        stmt.setString(2, tfPPSID.getText());
                        int rowsAffected = stmt.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.println("DELETE worked");
                        } else {
                            System.out.println("DELETE failed");
                        }
                        conn.close();

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    btnPPSUpdate.setDisable(true);
                    btnEditPPS.setDisable(false);
                    tfPPSID.setDisable(true);
                    tfPackId.setDisable(true);
                    btnPPSAdd.setDisable(true);
                    btnCancel.setDisable(true);
                    btnPPSDel.setDisable(true);
                }
            });
        }
    }
// DASHBOARD METHODS ==================================================================================================
    private void GetOptionsScene() { DashboardMethods.IconGetScene("SystemDiagnostics.fxml", btnOptions); }
    private void GetLoginsScene() { DashboardMethods.IconGetScene("Login.fxml", btnLogin); }
    private void GetHomeScene() { DashboardMethods.IconGetScene("Home.fxml", btnHome); }
    private void GetPrintScene() { DashboardMethods.IconGetScene("PrintTable.fxml", btnPrint); }
    private void GetUserScene() { DashboardMethods.IconGetScene("User.fxml", btnUser); }
}
