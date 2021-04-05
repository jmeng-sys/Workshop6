package sample;

import database.DAO;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.net.URL;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

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
import javafx.util.converter.DateTimeStringConverter;
import objects.Packages;
import objects.PackagesProductsSuppliers;

public class ControllerPackages {
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
    private TableView<ObservableList<String>> tblProducts;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnEditPPS;

    @FXML
    void handle(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert btnPrint != null : "fx:id=\"btnPrint\" was not injected: check your FXML file 'Packages.fxml'.";
        assert btnOptions != null : "fx:id=\"btnOptions\" was not injected: check your FXML file 'Packages.fxml'.";
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
        assert tblProducts != null : "fx:id=\"tblProducts\" was not injected: check your FXML file 'Packages.fxml'.";
        assert btnEditPPS != null : "fx:id=\"btnEditPPS\" was not injected: check your FXML file 'Packages.fxml'.";
//  MENU BAR BUTTONS ===================================================================================================
        btnExit.setOnMouseClicked(mouseEvent -> System.exit(0));

//Connect to Database for ComboBox
        try {
            Connection db = DAO.getConnection();
            Statement stat = db.createStatement();
            ResultSet rs = stat.executeQuery("select * from packages p ");

            ObservableList<Packages> pkgs = FXCollections.observableArrayList();
            while (rs.next()) {
                pkgs.add(new Packages(rs.getInt(1), rs.getString(2), rs.getTimestamp(3),
                        rs.getTimestamp(4), rs.getString(5), rs.getDouble(6),
                        rs.getDouble(7)));
            }

            cbPkg.setItems(pkgs);

            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        cbPkg.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Packages>() {
            @Override
            public void changed(ObservableValue<? extends Packages> observableValue, Packages packages, Packages t1) {
                tfPkgName.setText(t1.getPkgName());
                taDescription.setText(t1.getPkgDesc());
                tfPrice.setText(t1.getPkgBasePrice() + "");
                tfCommission.setText(t1.getPkgAgencyCommission() + "");
                dpStartDate.setValue(t1.getPkgStartDate().toLocalDateTime().toLocalDate());
                dpEndDate.setValue(t1.getPkgEndDate().toLocalDateTime().toLocalDate());

//Populates the table of products and suppliers for packages
tblProducts.getColumns().addAll();

            }
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
                        "                    join Suppliers s on s.SupplierId = ps.SupplierId";
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

            btnSave.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
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
                }

            });

            btnEdit.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    btnSave.setDisable(false);
                    btnEdit.setDisable(true);
                    tfPkgName.setDisable(false);
                    taDescription.setDisable(false);
                    tfPrice.setDisable(false);
                    tfCommission.setDisable(false);
                    dpStartDate.setDisable(false);
                    dpEndDate.setDisable(false);
                }
            });

            btnEditPPS.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                }
            });


        }
    }}
