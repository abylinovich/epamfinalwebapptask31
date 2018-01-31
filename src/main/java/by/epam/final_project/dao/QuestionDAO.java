package by.epam.final_project.dao;

import by.epam.final_project.dao.exception.DAOException;
import by.epam.final_project.entity.Question;

import java.util.List;

public interface QuestionDAO {

    List<Question> findRandomQuestion() throws DAOException;

    int getQuestionsCount() throws DAOException;

    List<Question> findQuestions(int page, int count) throws DAOException;

    Question findQuestion(int id) throws DAOException;

    List<Question> findQuestionsByUser(int id, int page, int count) throws DAOException;

    int getQuestionsCountByUser(int id) throws DAOException;

    void insertQuestion(Question question) throws DAOException;

}
