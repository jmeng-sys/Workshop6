package sample;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import objects.GUIMethods;


public class Controller {

    @FXML
    private Label lblAgentName;

    @FXML
    private Label dateTime;

    @FXML
    private FontAwesomeIcon btnUser;

    @FXML
    private HBox btnReports;

    @FXML
    private HBox btnSupplier;

    @FXML
    private HBox btnProducts;

    @FXML
    private HBox btnPackages;

    @FXML
    private FontAwesomeIcon btnPrint;

    @FXML
    private FontAwesomeIcon btnOptions;

    @FXML
    private FontAwesomeIcon btnLogin;

    @FXML
    private FontAwesomeIcon btnExit;

    @FXML
    private HBox btnAgents;

    @FXML
    private FontAwesomeIcon btnHome;

    @FXML
    void initialize() {
        assert lblAgentName != null : "fx:id=\"lblAgentName\" was not injected: check your FXML file 'Home.fxml'.";
        assert btnUser != null : "fx:id=\"btnUser\" was not injected: check your FXML file 'Home.fxml'.";
        assert btnReports != null : "fx:id=\"btnReports\" was not injected: check your FXML file 'Home.fxml'.";
        assert btnSupplier != null : "fx:id=\"btnSupplier\" was not injected: check your FXML file 'Home.fxml'.";
        assert btnProducts != null : "fx:id=\"btnProducts\" was not injected: check your FXML file 'Home.fxml'.";
        assert btnPackages != null : "fx:id=\"btnPackages\" was not injected: check your FXML file 'Home.fxml'.";
        assert btnPrint != null : "fx:id=\"btnPrint\" was not injected: check your FXML file 'Home.fxml'.";
        assert btnOptions != null : "fx:id=\"btnPrint1\" was not injected: check your FXML file 'Home.fxml'.";
        assert btnLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'Home.fxml'.";
        assert btnExit != null : "fx:id=\"btnExit\" was not injected: check your FXML file 'Home.fxml'.";
        assert dateTime != null : "fx:id=\"dateTime\" was not injected: check your FXML file 'Home.fxml'.";
        assert btnAgents != null : "fx:id=\"btnAgents\" was not injected: check your FXML file 'Home.fxml'.";
        assert btnHome != null : "fx:id=\"btnHome\" was not injected: check your FXML file 'Home.fxml'.";

//DASHBOARD BUTTONS ====================================================================================================
        btnReports.setOnMouseClicked(event -> GetReportsScene());
        btnPackages.setOnMouseClicked(event -> GetPackagesScene());
        btnSupplier.setOnMouseClicked(event -> GetSupplierScene());
        btnAgents.setOnMouseClicked(event -> GetAgentScene());
        btnProducts.setOnMouseClicked(event -> GetProductScene());

        btnExit.setOnMouseClicked(mouseEvent -> System.exit(0));
        btnPrint.setOnMouseClicked(event -> GetPrintScene());
        btnOptions.setOnMouseClicked(event -> GetOptionsScene());
        btnLogin.setOnMouseClicked(event -> GetLoginsScene());
        btnHome.setOnMouseClicked(event -> GetHomeScene());
//        btnUser.setOnMouseClicked(event -> { GetUserScene(); });
// DASHBOARD METHODS ===================================================================================================
        DashboardMethods.changeAgentName(lblAgentName);
        GUIMethods.GetDateTime(dateTime);
    }
// CONTROLLER METHODS ==================================================================================================
    private void GetReportsScene() { DashboardMethods.HboxGetScene("PrintTable.fxml", btnReports); }
    private void GetPackagesScene() { DashboardMethods.HboxGetScene("Packages.fxml", btnPackages); }
    private void GetSupplierScene() { DashboardMethods.HboxGetScene("Supplier.fxml", btnSupplier); }
    private void GetAgentScene() { DashboardMethods.HboxGetScene("Agent.fxml", btnAgents); }
    private void GetProductScene() { DashboardMethods.HboxGetScene("Products.fxml", btnProducts); }

    private void GetOptionsScene() { DashboardMethods.IconGetScene("SystemDiagnostics.fxml", btnOptions); }
    private void GetLoginsScene() { DashboardMethods.IconGetScene("Login.fxml", btnLogin); }
    private void GetHomeScene() { DashboardMethods.IconGetScene("Home.fxml", btnHome); }
    private void GetPrintScene() { DashboardMethods.IconGetScene("PrintTable.fxml", btnPrint); }
//    private void GetUserScene() { DashboardMethods.IconGetScene("User.fxml", btnUser); }
}