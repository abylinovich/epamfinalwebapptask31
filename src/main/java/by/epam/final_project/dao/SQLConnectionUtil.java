package by.epam.final_project.dao;

import java.sql.*;

public class SQLConnectionUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/epamapplicationdb";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "rootroot";

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
            return DriverManager.getConnection(URL, LOGIN, PASSWORD);
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

}
