package codenofix.com.example.File;

public class ConnectionData {
    private final String serverName;
    private final String databaseName;
    private final String username;
    private final String password;
    private final int port;

    public ConnectionData(String serverName, String databaseName, String username, String password, int port) {
        this.serverName = serverName;
        this.databaseName = databaseName;
        this.username = username;
        this.password = password;
        this.port = port;
    }

    public String getServerName() {
        return serverName;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getPort() {
        return port;
    }
}
