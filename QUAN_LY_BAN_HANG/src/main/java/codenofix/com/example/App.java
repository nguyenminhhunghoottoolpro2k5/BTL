package codenofix.com.example;

import codenofix.com.example.File.ConnectionChecker;
import codenofix.com.example.File.ConnectionData;
import codenofix.com.example.File.FileManager;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ConnectionData connectionData;
        boolean isConnected = false;
        String filePath = "ConnectDataBaseSQLServer.json";

        // Load connection data if available
        connectionData = FileManager.load(filePath);

        // Check connection using loaded data
        if (connectionData != null) {
            isConnected = ConnectionChecker.checkConnection(connectionData);
            if (isConnected) {
                System.out.println("Successfully connected using saved data.");
            } else {
                System.out.println("Saved data is invalid, please re-enter.");
            }
        }

        // Prompt for connection details if no valid connection data
        while (!isConnected) {
            System.out.print("Server IP: ");
            String serverName = sc.nextLine();
            System.out.print("Port (default 1433): ");
            String portInput = sc.nextLine();
            int port = portInput.isEmpty() ? 1433 : Integer.parseInt(portInput);
            System.out.print("Database name: ");
            String databaseName = sc.nextLine();
            System.out.print("Username: ");
            String usernameSQL = sc.nextLine();
            System.out.print("Password: ");
            String passwordSQL = sc.nextLine();

            connectionData = new ConnectionData(serverName, databaseName, usernameSQL, passwordSQL, port);
            isConnected = ConnectionChecker.checkConnection(connectionData);

            if (isConnected) {
                FileManager.save(filePath, connectionData);
                System.out.println("Connection successful and information saved.");
            } else {
                System.out.println("Connection failed. Please re-enter the information.");
            }
        }
        sc.close();
    }
}
