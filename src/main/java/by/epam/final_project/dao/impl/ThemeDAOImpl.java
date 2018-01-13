package by.epam.final_project.dao.impl;

import by.epam.final_project.dao.ThemeDAO;
import by.epam.final_project.dao.connectionpool.ConnectionPool;
import by.epam.final_project.dao.constant.DatabaseTable;
import by.epam.final_project.dao.exception.DAOException;
import by.epam.final_project.entity.Theme;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ThemeDAOImpl implements ThemeDAO {

    private static final Logger logger = Logger.getLogger(ThemeDAOImpl.class);

    private ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

    private String FIND_THEMES_QUERY =
            "SELECT t.theme_id, t.theme_title FROM themes t " +
            "JOIN locales l ON t.locale_id = l.locale_id " +
            "WHERE l.language = ?";
    
    
    @Override
    public List<Theme> findThemes(Locale locale) throws DAOException {
        List<Theme> result;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(FIND_THEMES_QUERY);
            statement.setString(1, locale.getLanguage());
            resultSet = statement.executeQuery();
            result = createThemesList(resultSet);
            logger.debug("Find all themes.");
            return result;

        } catch (SQLException e) {
            throw new DAOException("Cannot find themes.", e);
        } finally {
            connectionPool.close(connection, statement, resultSet);
        }
    }

    private List<Theme> createThemesList(ResultSet resultSet) throws SQLException {
        List<Theme> result = new ArrayList<>();
        while (resultSet.next()) {
            Theme theme = new Theme();

            int id = resultSet.getInt(DatabaseTable.Themes.THEME_ID);
            theme.setThemeId(id);

            String title = resultSet.getString(DatabaseTable.Themes.THEME_TITLE);
            theme.setTitle(title);

            result.add(theme);
        }
        return result;
    }
    
}
