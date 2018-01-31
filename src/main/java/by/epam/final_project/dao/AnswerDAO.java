package by.epam.final_project.dao;

import by.epam.final_project.dao.exception.DAOException;
import by.epam.final_project.entity.Answer;

public interface AnswerDAO {

    void insertAnswer(Answer answer) throws DAOException;

}
