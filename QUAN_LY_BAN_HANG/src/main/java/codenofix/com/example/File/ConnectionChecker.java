package codenofix.com.example.File;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionChecker {
    public static boolean checkConnection(ConnectionData connectionData) {
        String url = "jdbc:sqlserver://" + connectionData.getServerName() + ":" + connectionData.getPort()
                + ";databaseName=" + connectionData.getDatabaseName();
        try (Connection connection = DriverManager.getConnection(url, connectionData.getUsername(), connectionData.getPassword())) {
            System.out.println("Successfully connected to the database: " + connectionData.getDatabaseName());
            return true;
        } catch (SQLException e) {
            System.out.println("Connection error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
