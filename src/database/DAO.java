package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface DAO {
    String db_url = "jdbc:mysql://localhost:3306/travelexperts";
    String db_username = "root";
    String db_password = "";

    static Connection getConnection() {
        Connection conn = null;
        try
        {
            conn = DriverManager.getConnection(db_url, db_username, db_password);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return conn;
    }
}
