package by.epam.final_project.dao;

import by.epam.final_project.exception.DAOException;

import java.sql.*;

import static by.epam.final_project.exception.message.DAOExceptionMessageUtil.CANNOT_CREATE_CONNECTION_MESSAGE;
import static by.epam.final_project.exception.message.DAOExceptionMessageUtil.CANNOT_CREATE_STATEMENT_MESSAGE;
import static by.epam.final_project.exception.message.DAOExceptionMessageUtil.CANNOT_EXECUTE_QUERY_MESSAGE;
import static by.epam.final_project.exception.message.DAOExceptionMessageUtil.CANNOT_EXECUTE_UPDATE_MESSAGE;
import static by.epam.final_project.exception.message.DAOExceptionMessageUtil.CANNOT_CLOSE_CONNECTION_MESSAGE;

public class SQLConnectionUtil {

    // TODO: 23.11.2017 use prop
    private static final String URL = "jdbc:mysql://localhost:3306/epamapplicationdb";
    private static final String ENCODING_CHARSET = "?useUnicode=true&characterEncoding=UTF-8";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "rootroot";

    public static Connection getConnection() throws DAOException {
        try {
            return DriverManager.getConnection(URL + ENCODING_CHARSET, LOGIN, PASSWORD);
        } catch (SQLException e) {
            throw new DAOException(CANNOT_CREATE_CONNECTION_MESSAGE, e);
        }
    }

    public static Statement createStatement(Connection connection) throws DAOException {
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            throw new DAOException(CANNOT_CREATE_STATEMENT_MESSAGE, e);
        }
    }

    public static ResultSet executeQuery(Statement statement, String query) throws DAOException {
        try {
            return statement.executeQuery(query);
        } catch (SQLException e) {
            throw new DAOException(CANNOT_EXECUTE_QUERY_MESSAGE, e);
        }
    }

    public static void executeUpdate(Statement statement, String query) throws DAOException {
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new DAOException(CANNOT_EXECUTE_UPDATE_MESSAGE, e);
        }
    }

    public static void closeConnection(Connection connection) throws DAOException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DAOException(CANNOT_CLOSE_CONNECTION_MESSAGE, e);
        }
    }

}
