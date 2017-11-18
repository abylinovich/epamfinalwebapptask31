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

    @Override
    public User findUser(String login, String password) {
        User user = new User();
        Connection connection = SQLConnectionUtil.getConnection();
        Statement statement = SQLConnectionUtil.createStatement(connection);
        String whereCondition = " WHERE login = '" + login + "' AND password = '" + password + "'";
        ResultSet resultSet = SQLConnectionUtil.executeQuery(statement, FIND_USER_QUERY + whereCondition);
        try {
            if(resultSet.next()) {
                user.setLogin(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setFirstName(resultSet.getString(4));
                user.setLastName(resultSet.getString(5));
                user.setEmail(resultSet.getString(6));
                user.setAge(resultSet.getInt(7));
                return user;
            } else {
                throw new RuntimeException("No matches in database");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot create entity, database exception.");
        }

    }

}
