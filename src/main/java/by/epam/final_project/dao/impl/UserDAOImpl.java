package by.epam.final_project.dao.impl;

import by.epam.final_project.dao.SQLConnectionUtil;
import by.epam.final_project.dao.UserDAO;
import by.epam.final_project.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAOImpl implements UserDAO {

    private static final String FIND_USER_QUERY = "SELECT * FROM user";
    private static final String ADD_USER_QUERY = "INSERT INTO user";

    @Override
    public User findUserByLoginAndPassword(String login, String password) {
        User user = new User();
        Connection connection = SQLConnectionUtil.getConnection();
        Statement statement = SQLConnectionUtil.createStatement(connection);
        String whereCondition = createWhereCondition(login, password);
        ResultSet resultSet = SQLConnectionUtil.executeQuery(statement, FIND_USER_QUERY + whereCondition);
        try {
            if(resultSet.next()) {
                user.setLogin(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setFirstName(resultSet.getString(4));
                user.setLastName(resultSet.getString(5));
                user.setEmail(resultSet.getString(6));
                user.setAge(resultSet.getInt(7));
            } else {
                throw new RuntimeException("No matches in database");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot create entity, database exception.");
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
    public void createNewUser(String login, String password, String firstName, String lastName, String email, int age) {
        Connection connection = SQLConnectionUtil.getConnection();
        Statement statement = SQLConnectionUtil.createStatement(connection);
        String valuesCondition = createValuesCondition(login, password, firstName, lastName, email, age);
        SQLConnectionUtil.executeUpdate(statement, ADD_USER_QUERY + valuesCondition);
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
