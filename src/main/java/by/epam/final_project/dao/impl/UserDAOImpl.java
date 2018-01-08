package by.epam.final_project.dao.impl;

import by.epam.final_project.dao.UserDAO;
import by.epam.final_project.dao.connectionpool.ConnectionPool;
import by.epam.final_project.dao.constant.DatabaseTable;
import by.epam.final_project.entity.User;
import by.epam.final_project.dao.exception.DAOException;
import by.epam.final_project.entity.UserRole;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Locale;

public class UserDAOImpl implements UserDAO {

    private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

    private ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

    private String REGISTER_USER_QUERY =
            "INSERT INTO users (role, username, password, first_name, last_name, email, locale_id) " +
            "VALUES (?, ?, MD5(?), ?, ?, ?, (SELECT locale_id FROM locales l WHERE l.language = ?))";
    private String FIND_USER_QUERY =
            "SELECT u.role, u.username, u.first_name, u.last_name, u.email, l.language, l.country " +
                    "FROM users u " +
                    "JOIN locales l ON u.locale_id = l.locale_id " +
                    "WHERE u.username = ? AND u.password = MD5(?)";

    @Override
    public User findUser(String login, String password) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = connectionPool.getConnection();

            statement = connection.prepareStatement(FIND_USER_QUERY);

            statement.setString(1, login);
            statement.setString(2, password);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                String role = resultSet.getString(DatabaseTable.Users.ROLE);
                user.setRole(UserRole.valueOf(role.toUpperCase()));
                String username = resultSet.getString(DatabaseTable.Users.USERNAME);
                user.setUsername(username);
                String firstName = resultSet.getString(DatabaseTable.Users.FIRST_NAME);
                user.setFirstName(firstName);
                String lastName = resultSet.getString(DatabaseTable.Users.LAST_NAME);
                user.setLastName(lastName);
                String email = resultSet.getString(DatabaseTable.Users.EMAIL);
                user.setEmail(email);
                String language = resultSet.getString(DatabaseTable.Locales.LANGUAGE);
                String country = resultSet.getString(DatabaseTable.Locales.COUNTRY);
                user.setLocale(new Locale(language, country));
            }
            return user;

        } catch (SQLException e) {
            throw new DAOException("Failed to login user", e);
        } finally {
            connectionPool.close(connection, statement, resultSet);
        }
    }



    @Override
    public void register(User user) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = connectionPool.getConnection();

            statement = connection.prepareStatement(REGISTER_USER_QUERY);
            statement.setString(1, user.getRole().toString());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getFirstName());
            statement.setString(5, user.getLastName());
            statement.setString(6, user.getEmail());
            statement.setString(7, user.getLocale().getLanguage());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("Failed to register user", e);
        } finally {
            connectionPool.close(connection, statement);
        }
    }

}
