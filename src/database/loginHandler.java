package database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import objects.Agent;

import java.sql.*;

public class loginHandler
{
    
    private Connection getConnection()
    {
        Connection conn = null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "root", "");
        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
        return conn;
    }
}
