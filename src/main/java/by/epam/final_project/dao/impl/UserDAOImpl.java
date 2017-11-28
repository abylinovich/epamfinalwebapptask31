package by.epam.final_project.dao.impl;

import by.epam.final_project.dao.SQLConnectionUtil;
import by.epam.final_project.dao.UserDAO;
import by.epam.final_project.entity.User;
import by.epam.final_project.exception.DAOException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static by.epam.final_project.dao.SQLConnectionUtil.DATABASE_DRIVER_CLASS_PATH;
import static by.epam.final_project.dao.SQLQueryMessageUtil.SELECT_ALL_FROM_USER_QUERY;
import static by.epam.final_project.dao.SQLQueryMessageUtil.INSERT_INTO_USER_QUERY;
import static by.epam.final_project.exception.message.DAOExceptionMessageUtil.CANNOT_CREATE_ENTITY_MESSAGE;
import static by.epam.final_project.exception.message.DAOExceptionMessageUtil.DATABASE_DRIVER_LOAD_EXCEPTION_MESSAGE;
import static by.epam.final_project.exception.message.DAOExceptionMessageUtil.NO_MATCHES_IN_DATABASE_MESSAGE;

public class UserDAOImpl implements UserDAO {

    @Override
    public void init() throws DAOException {
        try {
            Class.forName(DATABASE_DRIVER_CLASS_PATH);
        } catch (ClassNotFoundException e) {
            throw new DAOException(DATABASE_DRIVER_LOAD_EXCEPTION_MESSAGE, e);
        }
    }

    @Override
    public User findUserByLoginAndPassword(String login, String password) throws DAOException {
        User user = new User();
        Connection connection = SQLConnectionUtil.getConnection();
        String whereCondition = createWhereCondition(login, password);
        ResultSet resultSet = SQLConnectionUtil.executeQuery(connection, SELECT_ALL_FROM_USER_QUERY + whereCondition);
        try {
            if(resultSet.next()) {
                user.setLogin(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setFirstName(resultSet.getString(4));
                user.setLastName(resultSet.getString(5));
                user.setEmail(resultSet.getString(6));
                user.setAge(resultSet.getInt(7));
            } else {
                throw new DAOException(NO_MATCHES_IN_DATABASE_MESSAGE);
            }
        } catch (SQLException e) {
            throw new DAOException(CANNOT_CREATE_ENTITY_MESSAGE, e);
        }
        SQLConnectionUtil.closeConnection(connection);
        return user;
    }

    private String createWhereCondition(String login, String password) {
        StringBuilder sb = new StringBuilder();
        sb.append(
                " WHERE login = '" + login + "'" +
                " AND password = '" + password + "'"
        );
        return sb.toString();
    }

    @Override
    public void createNewUser(String login, String password, String firstName, String lastName, String email, int age) throws DAOException {
        Connection connection = SQLConnectionUtil.getConnection();
        String valuesCondition = createValuesCondition(login, password, firstName, lastName, email, age);
        SQLConnectionUtil.executeUpdate(connection, INSERT_INTO_USER_QUERY + valuesCondition);
        SQLConnectionUtil.closeConnection(connection);
    }

    private String createValuesCondition(String login, String password, String firstName, String lastName, String email, int age) {
        StringBuilder sb = new StringBuilder();
        sb.append(
                " (login, password, first_name, last_name, email, age)" +
                " VALUES (" +
                "'" + login + "'" +
                ", '" + password + "'" +
                ", '" + firstName + "'" +
                ", '" + lastName + "'" +
                ", '" + email + "'" +
                ", " + age +
                ")"
        );
        return sb.toString();
    }

}
