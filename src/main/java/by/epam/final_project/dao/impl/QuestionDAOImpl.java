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
import java.util.ArrayList;
import java.util.List;

public class QuestionDAOImpl implements QuestionDAO {

    private static final Logger logger = Logger.getLogger(QuestionDAOImpl.class);

    private ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

    private String FIND_QUESTIONS_QUERY =
            "SELECT q.question_id, q.question_title, q.question, t.theme_title, u.user_id, u.username " +
                    "FROM questions q " +
                    "JOIN users u ON u.user_id = q.user_id " +
                    "JOIN themes t ON q.theme_id = t.theme_id AND t.locale_id = u.locale_id";

    private String FIND_RANDOM_QUESTION_QUERY =
            FIND_QUESTIONS_QUERY + " ORDER BY RAND() LIMIT 1";

    private String FIND_QUESTIONS_BY_USER_ID_QUERY =
            FIND_QUESTIONS_QUERY + " WHERE u.user_id = ?";

    private String FIND_QUESTION_BY_ID_QUERY =
            FIND_QUESTIONS_QUERY + " WHERE q.question_id = ?";

    private String INSERT_QUESTION_QUERY =
            "INSERT INTO questions (question_title, question, theme_id, user_id) VALUE (?, ?, ?, ?)";


    @Override
    public List<Question> findRandomQuestion()  throws DAOException {
        List<Question> result;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(FIND_RANDOM_QUESTION_QUERY);
            resultSet = statement.executeQuery();
            result = createQuestionsList(resultSet);
            logger.debug("Find random question.");
            return result;

        } catch (SQLException e) {
            throw new DAOException("Cannot find random question.", e);
        } finally {
            connectionPool.close(connection, statement, resultSet);
        }
    }

    @Override
    public List<Question> findQuestions() throws DAOException {
        List<Question> result;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(FIND_QUESTIONS_QUERY);
            resultSet = statement.executeQuery();
            result = createQuestionsList(resultSet);
            logger.debug("Find all questions.");
            return result;

        } catch (SQLException e) {
            throw new DAOException("Cannot find random question.", e);
        } finally {
            connectionPool.close(connection, statement, resultSet);
        }
    }

    @Override
    public List<Question> findQuestionsByUser(int id) throws DAOException {
        List<Question> result;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(FIND_QUESTIONS_BY_USER_ID_QUERY);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            result = createQuestionsList(resultSet);
            logger.debug("Find questions for user id='" + id + "'.");
            return result;

        } catch (SQLException e) {
            throw new DAOException("Cannot find random question.", e);
        } finally {
            connectionPool.close(connection, statement, resultSet);
        }
    }

    @Override
    public List<Question> findQuestion(int id) throws DAOException {
        List<Question> result;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(FIND_QUESTION_BY_ID_QUERY);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            result = createQuestionsList(resultSet);
            logger.debug("Find question id='" + id + "'.");
            return result;

        } catch (SQLException e) {
            throw new DAOException("Cannot find question id ='" + id + "'.", e);
        } finally {
            connectionPool.close(connection, statement, resultSet);
        }
    }

    @Override
    public void insertQuestion(Question question) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = connectionPool.getConnection();

            statement = connection.prepareStatement(INSERT_QUESTION_QUERY);
            statement.setString(1, question.getTitle());
            statement.setString(2, question.getQuestion());
            statement.setInt(3, question.getTheme().getThemeId());
            statement.setInt(4, question.getUser().getUserId());

            statement.executeUpdate();
            logger.debug("New question has been successfully insert into database.");

        } catch (SQLException e) {
            throw new DAOException("Failed to insert question", e);
        } finally {
            connectionPool.close(connection, statement);
        }
    }

    private List<Question> createQuestionsList(ResultSet resultSet) throws SQLException {
        List<Question> result = new ArrayList<>();
        while (resultSet.next()) {
            Question question = new Question();

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
            user.setUserId(resultSet.getInt(DatabaseTable.Users.USER_ID));
            user.setUsername(username);
            question.setUser(user);

            result.add(question);
        }
        return result;
    }

}
