package repository;

import java.sql.*;

public class DatabaseService implements AutoCloseable {

    private static DatabaseService instance;
    private String url;
    private String username;
    private String password;

    private DatabaseService() {
        url = "jdbc:mysql://127.0.0.1:3306/test?user=%s&password=%s&useJDBCCompliantTimezoneShift=true&serverTimezone=UTC";
        username = "test";
        password = "test";
    }

    public static DatabaseService getInstance() {
        if (instance == null) {
            instance = new DatabaseService();
        }
        return instance;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(String.format(url, username, password));
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void showTable(String name) throws SQLException {
        Connection connection = getConnection();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("select * from " + name);
            while (rs.next()) {
                System.out.println(rs.getLong("id") + " : " + rs.getString("category") + " : " + rs.getString("title") + " : " + rs.getInt("price") + " : " + rs.getString("status"));
            }
            System.out.println("----------------");
        }
    }

    public void dropTable(String query) throws SQLException {
        executeStatement(query, DatabaseActionName.DROP);
    }

    public void createTable(String query) throws SQLException {
        executeStatement(query, DatabaseActionName.CREATE);
    }

    private void executeStatement(String query, DatabaseActionName action) throws SQLException {
        try (Statement statement = getConnection().createStatement()) {
            statement.executeUpdate(query);
        }
    }

    @Override
    public void close() throws Exception {

    }
}
