package documents;

import lombok.SneakyThrows;

import java.sql.*;

public class DBConnection {

    private static DBConnection dbConnection;

    private final Connection connection;

    @SneakyThrows
    public DBConnection(String url) {
        connection = DriverManager.getConnection(url);
    }

    @SneakyThrows
    private DBConnection() {
        connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/cache.db");
    }

    @SneakyThrows
    public String getDocument(String gcsPath) {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM document WHERE path=?"
        );
        preparedStatement.setString(1, gcsPath);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.getString("parsed");
    }

    @SneakyThrows
    public void createDocument(String gcsPath, String parse) {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO document (path, parsed) VALUES (?, ?)"
        );
        preparedStatement.setString(1, gcsPath);
        preparedStatement.setString(2, parse);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public static DBConnection getInstance() {
        if (dbConnection == null) {
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }

    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
