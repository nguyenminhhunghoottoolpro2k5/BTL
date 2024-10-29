package codenofix.com.example.File;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    private static final Gson gson = new Gson(); // Static Gson instance for better performance

    public static void save(String filePath, ConnectionData connectionData) {
        String jsonContent = gson.toJson(connectionData);
        File file = new File(filePath);
        File parentDir = file.getParentFile();

        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        if (file.exists()) {
            System.out.println("File already exists, not overwriting: " + filePath);
        } else {
            try (FileWriter writer = new FileWriter(filePath)) {
                writer.write(jsonContent);
                System.out.println("Connection data saved to: " + filePath);
            } catch (IOException e) {
                System.out.println("Error writing file: " + e.getMessage());
            }
        }
    }

    public static ConnectionData load(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            try (FileReader reader = new FileReader(filePath)) {
                JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();

                String serverName = json.has("serverName") ? json.get("serverName").getAsString() : null;
                String databaseName = json.has("databaseName") ? json.get("databaseName").getAsString() : null;
                String username = json.has("username") ? json.get("username").getAsString() : null;
                String password = json.has("password") ? json.get("password").getAsString() : null;
                int port = json.has("port") ? json.get("port").getAsInt() : 1433; // Default port is 1433 if not present

                if (serverName != null && databaseName != null && username != null && password != null) {
                    return new ConnectionData(serverName, databaseName, username, password, port);
                } else {
                    System.out.println("JSON file is missing one or more connection details.");
                }
            } catch (IOException e) {
                System.out.println("Error reading connection data: " + e.getMessage());
            } catch (JsonSyntaxException e) {
                System.out.println("Invalid JSON syntax: " + e.getMessage());
            }
        } else {
            System.out.println("Connection data file not found: " + filePath);
        }
        return null;
    }
}
