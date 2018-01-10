package by.epam.final_project.service.impl;

import by.epam.final_project.dao.DAOFactory;
import by.epam.final_project.dao.QuestionDAO;
import by.epam.final_project.dao.exception.DAOException;
import by.epam.final_project.entity.Question;
import by.epam.final_project.service.QuestionService;
import by.epam.final_project.service.exception.ServiceException;
import by.epam.final_project.service.validator.ParameterValidator;
import by.epam.final_project.service.validator.ValidatorFactory;
import org.apache.log4j.Logger;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    private final static Logger logger = Logger.getLogger(QuestionServiceImpl.class);

    private ParameterValidator parameterValidator = ValidatorFactory.getInstance().getParameterValidator();
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
    public List<Question> getQuestion(String id) throws ServiceException {
        if(!parameterValidator.validateId(id)) {
            throw new ServiceException("Validation error. Invalid question id='" + id + "'.");
        }
        try {
            return questionDAO.findQuestion(Integer.valueOf(id));
        } catch (DAOException e) {
            throw new ServiceException("Cannot get question id='" + id + "'.", e);
        }
    }

    @Override
    public List<Question> getQuestionsByUser(String id) throws ServiceException {
        if(!parameterValidator.validateId(id)) {
            throw new ServiceException("Validation error. Invalid user id='" + id + "'.");
        }
        try {
            return questionDAO.findQuestionsByUser(Integer.valueOf(id));
        } catch (DAOException e) {
            throw new ServiceException("Cannot get questions for user id='" + id + "'.", e);
        }
    }

}
