package by.epam.finalproject.dao.impl;

import by.epam.finalproject.dao.QuestionDAO;
import by.epam.finalproject.dao.connectionpool.ConnectionPool;
import by.epam.finalproject.dao.constant.DatabaseTable;
import by.epam.finalproject.dao.exception.DAOException;
import by.epam.finalproject.entity.Answer;
import by.epam.finalproject.entity.Question;
import by.epam.finalproject.entity.Theme;
import by.epam.finalproject.entity.User;
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

    private String GET_QUESTIONS_COUNT_QUERY =
            "SELECT COUNT(q.question_id) FROM questions q";

    private String FIND_QUESTIONS_PORTION_QUERY =
            FIND_QUESTIONS_QUERY +  " ORDER BY q.question_id LIMIT ?,?";

    private String FIND_RANDOM_QUESTION_QUERY =
            FIND_QUESTIONS_QUERY + " ORDER BY RAND() LIMIT 1";

    private String FIND_QUESTIONS_BY_USER_ID_QUERY =
            FIND_QUESTIONS_QUERY + " WHERE u.user_id = ?";

    private String FIND_QUESTIONS_BY_USER_ID_PORTION_QUERY =
            FIND_QUESTIONS_BY_USER_ID_QUERY +  " ORDER BY q.question_id LIMIT ?,?";

    private String GET_QUESTIONS_COUNT_FOR_USER_QUERY =
            GET_QUESTIONS_COUNT_QUERY +
                    " JOIN users u ON u.user_id = q.user_id" +
                    " WHERE u.user_id = ?";

    private String FIND_QUESTION_BY_ID_QUERY =
            FIND_QUESTIONS_QUERY + " WHERE q.question_id = ?";

    private String FIND_ANSWERS_BY_QUESTION_ID_QUERY =
            "SELECT a.answer_id, a.answer, a.user_id, a.question_id, u.username " +
                    "FROM answers a " +
                    "JOIN users u ON a.user_id = u.user_id " +
                    "WHERE a.question_id = ?";

    private String INSERT_QUESTION_QUERY =
            "INSERT INTO questions (question_title, question, theme_id, user_id) VALUE (?, ?, ?, ?)";

    private String REMOVE_QUESTION_QUERY =
            "DELETE FROM questions WHERE question_id = ?";

    private String UPDATE_QUESTION_QUERY =
            "UPDATE questions SET question_title = ?, question = ? WHERE question_id = ?";


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
    public int getQuestionsCount() throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(GET_QUESTIONS_COUNT_QUERY);
            resultSet = statement.executeQuery();
            resultSet.next();
            int total = resultSet.getInt(1);
            logger.debug("Get total questions count = '" + total + "'.");
            return total;

        } catch (SQLException e) {
            throw new DAOException("Cannot get total questions count.", e);
        } finally {
            connectionPool.close(connection, statement, resultSet);
        }
    }

    @Override
    public List<Question> findQuestions(int page, int count) throws DAOException {
        int offset = page * count;

        List<Question> result;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(FIND_QUESTIONS_PORTION_QUERY);
            statement.setInt(1, offset);
            statement.setInt(2, count);
            resultSet = statement.executeQuery();
            result = createQuestionsList(resultSet);
            logger.debug("Find " + count + " questions for page " + page + "' and count '" + count + "'.");
            return result;

        } catch (SQLException e) {
            throw new DAOException("Cannot find random question.", e);
        } finally {
            connectionPool.close(connection, statement, resultSet);
        }
    }

    @Override
    public List<Question> findQuestionsByUser(int id, int page, int count) throws DAOException {
        int offset = page * count;

        List<Question> result;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(FIND_QUESTIONS_BY_USER_ID_PORTION_QUERY);
            statement.setInt(1, id);
            statement.setInt(2, offset);
            statement.setInt(3, count);
            resultSet = statement.executeQuery();
            result = createQuestionsList(resultSet);
            logger.debug("Find " + count + " questions for user id='" + id + "' for page '" + page + "'.");
            return result;

        } catch (SQLException e) {
            throw new DAOException("Cannot find random question.", e);
        } finally {
            connectionPool.close(connection, statement, resultSet);
        }
    }

    @Override
    public int getQuestionsCountByUser(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(GET_QUESTIONS_COUNT_FOR_USER_QUERY);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            resultSet.next();
            int total = resultSet.getInt(1);
            logger.debug("Get total questions count = '" + total + "' for user id = '" + id + "'.");
            return total;

        } catch (SQLException e) {
            throw new DAOException("Cannot get total questions count for user id = '" + id + "'.", e);
        } finally {
            connectionPool.close(connection, statement, resultSet);
        }
    }

    @Override
    public Question findQuestion(int id) throws DAOException {
        List<Question> question;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(FIND_QUESTION_BY_ID_QUERY);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            question = createQuestionsList(resultSet);
            if(question.size() == 0) {
                logger.debug("No questions found by id='" + id + "'.");
                return null;
            }
            logger.debug("Question id='" + id + "' was found.");

            statement = connection.prepareStatement(FIND_ANSWERS_BY_QUESTION_ID_QUERY);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            question.get(0).setAnswers(getAnswers(resultSet));
            logger.debug("Find answers for question id='" + id + "'.");

            return question.get(0);

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

    @Override
    public void updateQuestion(Question question) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = connectionPool.getConnection();

            statement = connection.prepareStatement(UPDATE_QUESTION_QUERY);
            statement.setString(1, question.getTitle());
            statement.setString(2, question.getQuestion());
            statement.setInt(3, question.getQuestionId());

            statement.executeUpdate();
            logger.debug("Question has been successfully update.");

        } catch (SQLException e) {
            throw new DAOException("Failed to update question", e);
        } finally {
            connectionPool.close(connection, statement);
        }
    }

    @Override
    public void removeQuestion(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = connectionPool.getConnection();

            statement = connection.prepareStatement(REMOVE_QUESTION_QUERY);
            statement.setInt(1, id);

            statement.executeUpdate();
            logger.debug("Question has been removed successfully from database.");

        } catch (SQLException e) {
            throw new DAOException("Failed to remove question", e);
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

            question.setUser(getUser(resultSet));

            result.add(question);
        }
        return result;
    }

    private List<Answer> getAnswers(ResultSet resultSet) throws SQLException {
        List<Answer> result = new ArrayList<>();
        while (resultSet.next()) {
            Answer answer = new Answer();

            int answerId = resultSet.getInt(DatabaseTable.Answers.ANSWER_ID);
            answer.setAnswerId(answerId);

            String answerData = resultSet.getString(DatabaseTable.Answers.ANSWER);
            answer.setAnswer(answerData);

            answer.setUser(getUser(resultSet));

            result.add(answer);
        }
        return result;
    }

    private User getUser(ResultSet resultSet) throws SQLException {
        User user = new User();

        Integer userId = resultSet.getInt(DatabaseTable.Users.USER_ID);
        user.setUserId(userId);

        String username = resultSet.getString(DatabaseTable.Users.USERNAME);
        user.setUsername(username);

        return user;
    }

}
