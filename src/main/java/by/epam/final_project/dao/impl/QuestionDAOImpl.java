package by.epam.final_project.dao.impl;

import by.epam.final_project.dao.QuestionDAO;
import by.epam.final_project.dao.connectionpool.ConnectionPool;
import by.epam.final_project.dao.constant.DatabaseTable;
import by.epam.final_project.dao.exception.DAOException;
import by.epam.final_project.entity.Question;
import by.epam.final_project.entity.Theme;
import by.epam.final_project.entity.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionDAOImpl implements QuestionDAO {

    private static final Logger logger = Logger.getLogger(QuestionDAOImpl.class);

    private ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

    private String FIND_RANDOM_QUESTION_QUERY =
            "SELECT q.question_id, q.question_title, q.question, t.theme_title, u.username " +
                    "FROM questions q " +
                    "JOIN users u ON u.user_id = q.user_id " +
                    "JOIN themes t ON q.theme_id = t.theme_id AND t.locale_id = u.locale_id " +
                    "ORDER BY RAND() LIMIT 1";

    @Override
    public Question findRandomQuestion()  throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Question question = null;
        try {
            connection = connectionPool.getConnection();

            statement = connection.prepareStatement(FIND_RANDOM_QUESTION_QUERY);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                question = new Question();

                int id = resultSet.getInt(DatabaseTable.Questions.QUESTION_ID);
                question.setQuestionId(id);

                String title = resultSet.getString(DatabaseTable.Questions.QUESTION_TITLE);
                question.setTitle(title);

                String questionData = resultSet.getString(DatabaseTable.Questions.QUESTION);
                question.setQuestion(questionData);

                String themeTitle = resultSet.getString(DatabaseTable.Themes.THEME_TITLE);
                Theme theme = new Theme();
                theme.setTitle(themeTitle);
                question.setTheme(theme);

                String username = resultSet.getString(DatabaseTable.Users.USERNAME);
                User user = new User();
                user.setUsername(username);
                question.setUser(user);
            }
            logger.debug("Find random question.");
            return question;

        } catch (SQLException e) {
            throw new DAOException("Cannot find random question.", e);
        } finally {
            connectionPool.close(connection, statement, resultSet);
        }

    }

}
