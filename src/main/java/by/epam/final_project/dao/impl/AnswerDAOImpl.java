package by.epam.final_project.dao.impl;

import by.epam.final_project.dao.AnswerDAO;
import by.epam.final_project.dao.connectionpool.ConnectionPool;
import by.epam.final_project.dao.exception.DAOException;
import by.epam.final_project.entity.Answer;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AnswerDAOImpl implements AnswerDAO {

    private static final Logger logger = Logger.getLogger(AnswerDAOImpl.class);

    private String INSERT_ANSWER_QUERY =
            "INSERT INTO answers (answer, question_id, user_id) VALUE (?, ?, ?)";

    private ConnectionPool connectionPool = ConnectionPool.getConnectionPool();


    @Override
    public void insertAnswer(Answer answer) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = connectionPool.getConnection();

            statement = connection.prepareStatement(INSERT_ANSWER_QUERY);
            statement.setString(1, answer.getAnswer());
            statement.setInt(2, answer.getQuestion().getQuestionId());
            statement.setInt(3, answer.getUser().getUserId());

            statement.executeUpdate();
            logger.debug("New answer has been successfully insert into database.");

        } catch (SQLException e) {
            throw new DAOException("Failed to insert answer", e);
        } finally {
            connectionPool.close(connection, statement);
        }
    }

}
