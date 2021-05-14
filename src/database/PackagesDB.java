package database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import objects.Agent;
import objects.Packages;

import java.sql.*;

public class PackagesDB {
    public static ObservableList<Packages> FetchPackages() {
        ObservableList<Packages> pkgs = FXCollections.observableArrayList();
        try {
            Connection db = DAO.getConnection();
            Statement stat = db.createStatement();
            ResultSet rs = stat.executeQuery("select * from packages p ");


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
            db.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pkgs;
    }

    public static void EditPackages (Long PackageId,
                                     String PkgName,
                                     Date PkgStartDate,
                                     Date PkgEndDate,
                                     String PkgDesc,
                                     Double PkgBasePrice,
                                     Double PkgAgencyCommission) {
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
            stmt.setLong(1, PackageId);
            stmt.setString(2, PkgName);
            stmt.setDate(3, PkgStartDate);
            stmt.setDate(4, PkgEndDate);
            stmt.setString(5, PkgDesc);
            stmt.setDouble(6, PkgBasePrice);
            stmt.setDouble(7, PkgAgencyCommission);
            stmt.setLong(8, PackageId);

            int rowsAffected = stmt.executeUpdate();
            String output = (rowsAffected > 0) ? "Successful" : "Failed";
            System.out.println(output);

            conn.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void AddPackage(String PkgName,
                                  Date PkgStartDate,
                                  Date PkgEndDate,
                                  String PkgDesc,
                                  Double PkgBasePrice,
                                  Double PkgAgencyCommission) {
        String sql = "INSERT INTO packages (" +
                "PkgName, " +
                "PkgStartDate, " +
                "PkgEndDate, " +
                "PkgDesc, " +
                "PkgBasePrice, " +
                "PkgAgencyCommission) " +
                "VALUES (? , ?, ?, ?, ?, ?)";
        Connection conn = DAO.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, PkgName);
            stmt.setString(2, PkgStartDate.toString());
            stmt.setString(3, PkgEndDate.toString());
            stmt.setString(4, PkgDesc);
            stmt.setString(5, PkgBasePrice.toString());
            stmt.setString(6, PkgAgencyCommission.toString());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Package added");
            } else {
                System.out.println("Add failed");
            }
            conn.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void DeletePackage(String tfPkgName){
        String sql = "DELETE FROM packages WHERE PkgName=?";
        Connection conn = DAO.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, tfPkgName);
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
    }
}
