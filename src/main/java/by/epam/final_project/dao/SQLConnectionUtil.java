package by.epam.final_project.dao;

import java.sql.*;

public class SQLConnectionUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/epamapplicationdb";
    private static final String ENCODING_CHARSET = "?useUnicode=true&characterEncoding=UTF-8";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "rootroot";

    static {
        establishDatabaseDriver();
    }

    public static void establishDatabaseDriver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot found database driver.");
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL + ENCODING_CHARSET, LOGIN, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot create connection.");
        }
    }

    public static Statement createStatement(Connection connection) {
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot create statement.");
        }
    }

    public static ResultSet executeQuery(Statement statement, String query) {
        try {
            return statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Query exception.");
        }
    }

    public static void executeUpdate(Statement statement, String query) {
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Update query exception.");
        }
    }

    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot close connection.");
        }
    }

}
