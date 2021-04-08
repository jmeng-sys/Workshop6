package sample;

import database.AgentDB;
import database.DAO;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import objects.Agent;
import objects.GUIMethods;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ControllerAgent {

    private ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
    private Boolean saveMode = false;

    @FXML
    private FontAwesomeIcon btnPrint;

    @FXML
    private FontAwesomeIcon btnOptions;

    @FXML
    private FontAwesomeIcon btnLogin;

    @FXML
    private FontAwesomeIcon btnUser;

    @FXML
    private Label lblAgentName;

    @FXML
    private Label dateTime;

    @FXML
    private FontAwesomeIcon btnExit;

    @FXML
    private FontAwesomeIcon btnAddAgent;

    @FXML
    private FontAwesomeIcon btnEditAgent;

    @FXML
    private ComboBox<Agent> cbAgents;

    @FXML
    private TextField txtAgentFirstName;

    @FXML
    private TextField txtAgentMiddleInitial;

    @FXML
    private TextField txtAgentLastName;

    @FXML
    private TextField txtAgentPhoneNumber;

    @FXML
    private TextField txtAgentEmail;

    @FXML
    private TextField txtAgentPosition;

    @FXML
    private ComboBox<String> cbAgencyId;

    @FXML
    private FontAwesomeIcon btnSaveAgent;

    @FXML
    private TableView<ObservableList<String>> tvAllAgents;

    @FXML
    private TableView<ObservableList<String>> tvAssociatedCustomers;

    @FXML
    private TextField txtAgentId;

    @FXML
    private FontAwesomeIcon btnPrevAgent;

    @FXML
    private FontAwesomeIcon btnNextAgent;

    @FXML
    private TextField txtAgentAgencyId;

    @FXML
    private FontAwesomeIcon btnDelete;

    @FXML
    private FontAwesomeIcon btnHome;

    @FXML
    void initialize() {
        assert btnPrint != null : "fx:id=\"btnPrint\" was not injected: check your FXML file 'Agent.fxml'.";
        assert btnOptions != null : "fx:id=\"btnOptions\" was not injected: check your FXML file 'Agent.fxml'.";
        assert btnLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'Agent.fxml'.";
        assert btnUser != null : "fx:id=\"btnUser\" was not injected: check your FXML file 'Agent.fxml'.";
        assert lblAgentName != null : "fx:id=\"lblAgentName\" was not injected: check your FXML file 'Agent.fxml'.";
        assert dateTime != null : "fx:id=\"dateTime\" was not injected: check your FXML file 'Agent.fxml'.";
        assert btnExit != null : "fx:id=\"btnExit\" was not injected: check your FXML file 'Agent.fxml'.";
        assert btnAddAgent != null : "fx:id=\"btnAddAgent\" was not injected: check your FXML file 'Agent.fxml'.";
        assert btnEditAgent != null : "fx:id=\"btnEditAgent\" was not injected: check your FXML file 'Agent.fxml'.";
        assert cbAgents != null : "fx:id=\"cbAgents\" was not injected: check your FXML file 'Agent.fxml'.";
        assert txtAgentFirstName != null : "fx:id=\"txtAgentFirstName\" was not injected: check your FXML file 'Agent.fxml'.";
        assert txtAgentMiddleInitial != null : "fx:id=\"txtAgentMiddleInitial\" was not injected: check your FXML file 'Agent.fxml'.";
        assert txtAgentLastName != null : "fx:id=\"txtAgentLastName\" was not injected: check your FXML file 'Agent.fxml'.";
        assert txtAgentPhoneNumber != null : "fx:id=\"txtAgentPhoneNumber\" was not injected: check your FXML file 'Agent.fxml'.";
        assert txtAgentEmail != null : "fx:id=\"txtAgentEmail\" was not injected: check your FXML file 'Agent.fxml'.";
        assert txtAgentPosition != null : "fx:id=\"txtAgentPosition\" was not injected: check your FXML file 'Agent.fxml'.";
        assert cbAgencyId != null : "fx:id=\"cbAgencyId\" was not injected: check your FXML file 'Agent.fxml'.";
        assert btnSaveAgent != null : "fx:id=\"btnSaveAgent\" was not injected: check your FXML file 'Agent.fxml'.";
        assert tvAllAgents != null : "fx:id=\"tvAllAgents\" was not injected: check your FXML file 'Agent.fxml'.";
        assert tvAssociatedCustomers != null : "fx:id=\"tvAssociatedCustomers\" was not injected: check your FXML file 'Agent.fxml'.";
        assert txtAgentId != null : "fx:id=\"AgentId\" was not injected: check your FXML file 'Agent.fxml'.";
        assert btnPrevAgent != null : "fx:id=\"btnPrevAgent\" was not injected: check your FXML file 'Agent.fxml'.";
        assert btnNextAgent != null : "fx:id=\"btnNextAgent\" was not injected: check your FXML file 'Agent.fxml'.";
        assert txtAgentAgencyId != null : "fx:id=\"txtAgentAgencyId\" was not injected: check your FXML file 'Agent.fxml'.";
        assert btnDelete != null : "fx:id=\"btnDelete\" was not injected: check your FXML file 'Agent.fxml'.";
        assert btnHome != null : "fx:id=\"btnHome\" was not injected: check your FXML file 'Agent.fxml'.";


//DASHBOARD BUTTONS ====================================================================================================
        btnExit.setOnMouseClicked(mouseEvent -> System.exit(0));
        btnPrint.setOnMouseClicked(event -> GetPrintScene());
        btnOptions.setOnMouseClicked(event -> GetOptionsScene());
        btnLogin.setOnMouseClicked(event -> GetLoginsScene());
        btnHome.setOnMouseClicked(event -> GetHomeScene());
//        btnUser.setOnMouseClicked(event -> { GetUserScene(); });
// DASHBOARD METHODS ===================================================================================================
        DashboardMethods.changeAgentName(lblAgentName);
        GUIMethods.GetDateTime(dateTime);
// AGENT METHOD CALLS ==================================================================================================
        btnNextAgent.setOnMouseClicked(event -> cbAgents.getSelectionModel().selectNext());
        btnPrevAgent.setOnMouseClicked(event -> cbAgents.getSelectionModel().selectPrevious());
        InitializeAgentComboBox();
        btnEditAgent.setOnMouseClicked(event -> ToggleEditableAgentFields(true));
        btnSaveAgent.setOnMouseClicked(event -> SaveNewOrEditedAgent());
        btnAddAgent.setOnMouseClicked(event -> ClearFieldsForAdd());
        btnDelete.setOnMouseClicked(event -> DeleteSelectedAgent());
    }
// DASHBOARD METHODS ===================================================================================================
    private void GetOptionsScene() { DashboardMethods.IconGetScene("SystemDiagnostics.fxml", btnOptions); }
    private void GetLoginsScene() { DashboardMethods.IconGetScene("Login.fxml", btnLogin); }
    private void GetHomeScene() { DashboardMethods.IconGetScene("Home.fxml", btnHome); }
    private void GetPrintScene() { DashboardMethods.IconGetScene("PrintTable.fxml", btnPrint); }
//    private void GetUserScene() { DashboardMethods.IconGetScene("User.fxml", btnUser); }
// AGENT METHODS =======================================================================================================
    private void InitializeAgentComboBox() {
        setCBList();
        cbAgents.getSelectionModel().selectFirst();
        buildAgentsTable();
        buildAssociatedCustomersTable();
        cbAgents.getSelectionModel().selectedItemProperty().addListener((observableValue) -> buildAssociatedCustomersTable());
        cbAgencyId.setItems(setAgenciesCB());
    }

    private void SaveNewOrEditedAgent() {
        ToggleEditableAgentFields(false);
        if (!saveMode) {
            AgentDB.UpdateAgent(
                    Integer.parseInt(txtAgentId.getText()),
                    txtAgentFirstName.getText(),
                    txtAgentMiddleInitial.getText(),
                    txtAgentLastName.getText(),
                    txtAgentPhoneNumber.getText(),
                    txtAgentEmail.getText(),
                    txtAgentPosition.getText(),
                    Integer.parseInt(txtAgentAgencyId.getText()));
        } else {
            AgentDB.AddAgent
                    (
                            txtAgentFirstName.getText(),
                            txtAgentMiddleInitial.getText(),
                            txtAgentLastName.getText(),
                            txtAgentPhoneNumber.getText(),
                            txtAgentEmail.getText(),
                            txtAgentPosition.getText(),
                            Integer.parseInt(txtAgentAgencyId.getText())
                    );
            saveMode = false;
            cbAgents.setDisable(false);
            btnNextAgent.setDisable(false);
            btnPrevAgent.setDisable(false);
        }
        cbAgents.setItems(AgentDB.FetchAgentList());

        if (txtAgentId.getText().equals("")) cbAgents.getSelectionModel().selectLast();
        else cbAgents.getSelectionModel().select(Integer.parseInt(txtAgentId.getText()) - 1);
        buildAgentsTable();
    }

    private void setCBList() {
        cbAgents.setItems(AgentDB.FetchAgentList());
        cbAgents.getSelectionModel().selectedItemProperty().addListener((observableValue, agent, t1) -> {
            if(t1 != null) {
                txtAgentId.setText(t1.getAgentId() + "");
                txtAgentEmail.setText(t1.getAgtEmail() + "");
                txtAgentMiddleInitial.setText(t1.getAgtMiddleInitial() + "");
                txtAgentFirstName.setText(t1.getAgtFirstName() + "");
                txtAgentLastName.setText(t1.getAgtLastName() + "");
                txtAgentPosition.setText(t1.getAgtPosition() + "");
                txtAgentPhoneNumber.setText(t1.getAgtBusPhone() + "");
                txtAgentAgencyId.setText(t1.getAgencyId() + "");
            }
        });
    }

    private void ClearFieldsForAdd() {
        cbAgents.getSelectionModel().clearSelection();
        cbAgents.setDisable(true);
        btnNextAgent.setDisable(true);
        btnPrevAgent.setDisable(true);
        txtAgentId.setText("");
        txtAgentFirstName.setText("");
        txtAgentMiddleInitial.setText("");
        txtAgentLastName.setText("");
        txtAgentPhoneNumber.setText("");
        txtAgentEmail.setText("");
        txtAgentPosition.setText("");
        txtAgentAgencyId.setText("");
        ToggleEditableAgentFields(true);
        saveMode = true;
    }

    private static ObservableList<String> setAgenciesCB() {
        ObservableList<String> agenciesList = FXCollections.observableArrayList();
        try {
            Connection conn = DAO.getConnection();

            Statement myStmt = conn.createStatement();
            ResultSet rs = myStmt.executeQuery(
                    "select *" +
                            "from agencies"
            );
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getString(i));
                }
                agenciesList.add(String.valueOf(row));
            }
            conn.close();
            return agenciesList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return agenciesList;
    }

    public void buildAgentsTable(){
        Connection c ;
        data = FXCollections.observableArrayList();
        try{
            c = DAO.getConnection();
            String SQL = "SELECT * FROM agents";
            ResultSet rs = c.createStatement().executeQuery(SQL);
            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                final int j = i;
                TableColumn<ObservableList<String>, String> tableColumn = new TableColumn<>(rs.getMetaData().getColumnName(i + 1));
                tableColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(j)));
                tvAllAgents.getColumns().add(tableColumn);
            }
            while(rs.next()){
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    row.add(rs.getString(i));
                }
                data.add(row);
            }
            tvAllAgents.getItems().addAll(data);
            c.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void buildAssociatedCustomersTable() {
        tvAssociatedCustomers.getItems().clear();
        tvAssociatedCustomers.getColumns().clear();
        if (tvAssociatedCustomers.getItems().isEmpty() && txtAgentId.getText() != "") {
            Connection c;
            data = FXCollections.observableArrayList();
            try {
                c = DAO.getConnection();
                String SQL = "SELECT * FROM customers WHERE AgentId = " + txtAgentId.getText();
                ResultSet rs = c.createStatement().executeQuery(SQL);
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    final int j = i;
                    TableColumn<ObservableList<String>, String> tableColumn = new TableColumn<>(rs.getMetaData().getColumnName(i + 1));
                    tableColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(j)));
                    tvAssociatedCustomers.getColumns().add(tableColumn);
                }
                while (rs.next()) {
                    ObservableList<String> row = FXCollections.observableArrayList();
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        row.add(rs.getString(i));
                    }
                    data.add(row);
                }
                tvAssociatedCustomers.getItems().addAll(data);
                c.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error on Building Data");
            }
        }
    }

    private void ToggleEditableAgentFields(boolean b) {
        txtAgentFirstName.setEditable(b);
        txtAgentMiddleInitial.setEditable(b);
        txtAgentLastName.setEditable(b);
        txtAgentPhoneNumber.setEditable(b);
        txtAgentEmail.setEditable(b);
        txtAgentAgencyId.setEditable(b);
        txtAgentPosition.setEditable(b);
        if(!b) {
            btnSaveAgent.setVisible(false);
            btnEditAgent.setVisible(true);
            btnAddAgent.setVisible(true);
        }
        else if(b) {
            btnEditAgent.setVisible(false);
            btnSaveAgent.setVisible(true);
            btnAddAgent.setVisible(false);
        }
    }
}