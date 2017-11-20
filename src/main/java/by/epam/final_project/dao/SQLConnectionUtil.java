package by.epam.final_project.dao;

import by.epam.final_project.exception.DAOException;

import java.sql.*;

public class SQLConnectionUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/epamapplicationdb";
    private static final String ENCODING_CHARSET = "?useUnicode=true&characterEncoding=UTF-8";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "rootroot";

    public static Connection getConnection() throws DAOException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new DAOException("Cannot found database driver.");
        }
        try {
            return DriverManager.getConnection(URL + ENCODING_CHARSET, LOGIN, PASSWORD);
        } catch (SQLException e) {
            throw new DAOException("Cannot create connection.");
        }
    }

    public static Statement createStatement(Connection connection) throws DAOException {
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            throw new DAOException("Cannot create statement.");
        }
    }

    public static ResultSet executeQuery(Statement statement, String query) throws DAOException {
        try {
            return statement.executeQuery(query);
        } catch (SQLException e) {
            throw new DAOException("Query exception.");
        }
    }

    public static void executeUpdate(Statement statement, String query) throws DAOException {
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new DAOException("Update query exception.");
        }
    }

    public static void closeConnection(Connection connection) throws DAOException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DAOException("Cannot close connection.");
        }
    }

}
