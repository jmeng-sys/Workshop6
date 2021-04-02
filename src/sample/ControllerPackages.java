package sample;

import database.DAO;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import objects.Packages;

public class ControllerPackages {

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
    private TableView<?> tblProducts;

    @FXML
    private TableColumn<?, ?> colProduct;

    @FXML
    private TableColumn<?, ?> colSupplier;

    @FXML
    private Button btnSave;

    @FXML
    void initialize() {
        assert btnPrint != null : "fx:id=\"btnPrint\" was not injected: check your FXML file 'Packages.fxml'.";
        assert btnOptions != null : "fx:id=\"btnOptions\" was not injected: check your FXML file 'Packages.fxml'.";
        assert btnUser != null : "fx:id=\"btnUser\" was not injected: check your FXML file 'Packages.fxml'.";
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
        assert tblProducts != null : "fx:id=\"tblProducts\" was not injected: check your FXML file 'Packages.fxml'.";
        assert colProduct != null : "fx:id=\"colProduct\" was not injected: check your FXML file 'Packages.fxml'.";
        assert colSupplier != null : "fx:id=\"colSupplier\" was not injected: check your FXML file 'Packages.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'Packages.fxml'.";

//Connect to Database for ComboBox
        try {
            Connection db = DAO.getConnection();
            Statement stat = db.createStatement();
            ResultSet rs = stat.executeQuery("select * from packages p " +
                    "join Packages_Products_Suppliers pps on pps.PackageId = p.PackageId " +
                    "join Products_Suppliers ps on ps.ProductSupplierId = pps.ProductSupplierId " +
                    "join Products prod on prod.ProductId = ps.ProductId " +
                    "join Suppliers s on s.SupplierId = ps.SupplierId");

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
            }
        });

    }
}
