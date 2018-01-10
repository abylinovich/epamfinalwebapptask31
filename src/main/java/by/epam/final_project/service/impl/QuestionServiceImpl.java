package by.epam.final_project.service.impl;

import by.epam.final_project.dao.DAOFactory;
import by.epam.final_project.dao.QuestionDAO;
import by.epam.final_project.dao.exception.DAOException;
import by.epam.final_project.entity.Question;
import by.epam.final_project.service.QuestionService;
import by.epam.final_project.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    private final static Logger logger = Logger.getLogger(QuestionServiceImpl.class);

    private QuestionDAO questionDAO = DAOFactory.getInstance().getQuestionDAO();

    @Override
    public List<Question> getRandomQuestion() throws ServiceException {
        try {
            return questionDAO.findRandomQuestion();
        } catch (DAOException e) {
            throw new ServiceException("Cannot get random question.", e);
        }
    }

    @Override
    public List<Question> getQuestions() throws ServiceException {
        try {
            return questionDAO.findQuestions();
        } catch (DAOException e) {
            throw new ServiceException("Cannot get all questions.", e);
        }
    }

    @Override
    public List<Question> getQuestions(String username) throws ServiceException {
        try {
            return questionDAO.findQuestions(username);
        } catch (DAOException e) {
            throw new ServiceException("Cannot get my questions.", e);
        }
    }

}
