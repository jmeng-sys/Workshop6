package database;

        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import objects.Agent;

        import java.sql.*;

public class agentdb implements DAO{
    public static ObservableList<Agent> FetchAgentList() {
        ObservableList<Agent> agentList = FXCollections.observableArrayList();
        try {
            Connection conn = DAO.getConnection();

            Statement myStmt = conn.createStatement();
            ResultSet rs = myStmt.executeQuery(
                    "select *" +
                            "from agents"
            );

            while (rs.getString(1) != null) {
                agentList.add(new Agent(rs.getInt("agentId"),
                        rs.getString("agtFirstName"),
                        rs.getString("agtMiddleInitial"),
                        rs.getString("agtLastName"),
                        rs.getString("agtBusPhone"),
                        rs.getString("agtEmail"),
                        rs.getString("agtPosition"),
                        rs.getInt("agencyId")));
            }
            conn.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
        return agentList;
    }

    public static void UpdateAgent(int AgentId,
                                   String AgtFirstName,
                                   String AgtMiddleInitial,
                                   String AgtLastName,
                                   String AgtBusPhone,
                                   String AgtEmail,
                                   String AgtPosition,
                                   int AgencyId) {
        String sql = "UPDATE `agents` " +
                "SET `AgentId`=?," +
                "`AgtFirstName`=?," +
                "`AgtMiddleInitial`=?," +
                "`AgtLastName`=?," +
                "`AgtBusPhone`=?," +
                "`AgtEmail`=?," +
                "`AgtPosition`=?," +
                "`AgencyId`=? " +
                "WHERE AgentId=?";
        try {
            Connection conn = DAO.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, AgentId);
            stmt.setString(2, AgtFirstName);
            stmt.setString(3, AgtMiddleInitial);
            stmt.setString(4, AgtLastName);
            stmt.setString(5, AgtBusPhone);
            stmt.setString(6, AgtEmail);
            stmt.setString(7, AgtPosition);
            stmt.setInt(8, AgencyId);
            stmt.setInt(9, AgentId);

            int rowsAffected = stmt.executeUpdate();
            String output = (rowsAffected > 0) ? "Successful" : "Failed";
            System.out.println(output);

            conn.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }


}
