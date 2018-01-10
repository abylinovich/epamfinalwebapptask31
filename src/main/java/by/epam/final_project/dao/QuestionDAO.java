package by.epam.final_project.dao;

import by.epam.final_project.dao.exception.DAOException;
import by.epam.final_project.entity.Question;

import java.util.List;

public interface QuestionDAO {

    List<Question> findRandomQuestion() throws DAOException;

    List<Question> findQuestions() throws DAOException;

    List<Question> findQuestion(int id) throws DAOException;

    List<Question> findQuestionsByUser(int id) throws DAOException;

}
