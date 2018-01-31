package by.epam.finalproject.dao;

import by.epam.finalproject.dao.exception.DAOException;
import by.epam.finalproject.entity.Answer;

public interface AnswerDAO {

    void insertAnswer(Answer answer) throws DAOException;

}
