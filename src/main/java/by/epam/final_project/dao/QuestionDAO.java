package by.epam.final_project.dao;

import by.epam.final_project.dao.exception.DAOException;
import by.epam.final_project.entity.Question;

public interface QuestionDAO {

    Question findRandomQuestion() throws DAOException;

}
