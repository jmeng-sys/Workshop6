package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
    public static String db_url = "jdbc:mysql://localhost:3306/travelexperts";
    public static String db_username = "root";
    public static String db_password = "";

    public static Connection getConnection() {

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
