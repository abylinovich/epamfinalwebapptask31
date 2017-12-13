package by.epam.final_project.dao;

import by.epam.final_project.exception.DAOException;

import java.sql.*;

import static by.epam.final_project.exception.message.DAOExceptionMessageUtil.CANNOT_CREATE_CONNECTION_MESSAGE;
import static by.epam.final_project.exception.message.DAOExceptionMessageUtil.CANNOT_CREATE_STATEMENT_MESSAGE;
import static by.epam.final_project.exception.message.DAOExceptionMessageUtil.CANNOT_EXECUTE_QUERY_MESSAGE;
import static by.epam.final_project.exception.message.DAOExceptionMessageUtil.CANNOT_EXECUTE_UPDATE_MESSAGE;
import static by.epam.final_project.exception.message.DAOExceptionMessageUtil.CANNOT_CLOSE_CONNECTION_MESSAGE;

public class SQLConnectionUtil {// в условии задачи сказано, что надо использовать свой собственный пул соединений, а не просто класс, выполняющий отдельные операции

    // TODO: 23.11.2017 use prop
    private static final String URL = "jdbc:mysql://localhost:3306/epamapplicationdb";// эти данные следует читать из файла properties
    private static final String ENCODING_CHARSET = "?useUnicode=true&characterEncoding=UTF-8";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "rootroot";
    public static final String DATABASE_DRIVER_CLASS_PATH = "com.mysql.jdbc.Driver";

    public static Connection getConnection() throws DAOException {
        try {
            return DriverManager.getConnection(URL + ENCODING_CHARSET, LOGIN, PASSWORD);
        } catch (SQLException e) {
            throw new DAOException(CANNOT_CREATE_CONNECTION_MESSAGE, e);
        }
    }

    public static ResultSet executeQuery(Connection connection, String query) throws DAOException {
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            return statement.executeQuery(query);
        } catch (SQLException e) {
            throw new DAOException(CANNOT_EXECUTE_QUERY_MESSAGE, e);
        }
    }

    public static void executeUpdate(Connection connection, String query) throws DAOException {
        try {
            PreparedStatement statement = connection.prepareStatement(query);
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
