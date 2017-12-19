package by.epam.final_project.dao;

import by.epam.final_project.dao.exception.DAOException;

import java.sql.*;

public class SQLConnectionUtil {

    // TODO: 23.11.2017 use prop
    private static final String URL = "jdbc:mysql://localhost:3306/epamapplicationdb";
    private static final String ENCODING_CHARSET = "?useUnicode=true&characterEncoding=UTF-8";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "rootroot";
    public static final String DATABASE_DRIVER_CLASS_PATH = "com.mysql.jdbc.Driver";

    public static Connection getConnection() throws DAOException {
        try {
            return DriverManager.getConnection(URL + ENCODING_CHARSET, LOGIN, PASSWORD);
        } catch (SQLException e) {
            throw new DAOException("Cannot create connection.", e);
        }
    }

    public static ResultSet executeQuery(Connection connection, String query) throws DAOException {
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            return statement.executeQuery(query);
        } catch (SQLException e) {
            throw new DAOException("Cannot execute query.", e);
        }
    }

    public static void executeUpdate(Connection connection, String query) throws DAOException {
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new DAOException("Cannot execute update.", e);
        }
    }

    public static void closeConnection(Connection connection) throws DAOException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DAOException("Cannot close connection.", e);
        }
    }

}
