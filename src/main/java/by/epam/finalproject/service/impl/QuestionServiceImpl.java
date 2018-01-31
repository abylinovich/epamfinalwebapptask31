package by.epam.finalproject.service.impl;

import by.epam.finalproject.dao.DAOFactory;
import by.epam.finalproject.dao.QuestionDAO;
import by.epam.finalproject.dao.exception.DAOException;
import by.epam.finalproject.entity.Question;
import by.epam.finalproject.service.QuestionService;
import by.epam.finalproject.service.exception.ServiceException;
import by.epam.finalproject.service.validator.ParameterValidator;
import by.epam.finalproject.service.validator.QuestionValidator;
import by.epam.finalproject.service.validator.ValidatorFactory;
import org.apache.log4j.Logger;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    private final static Logger logger = Logger.getLogger(QuestionServiceImpl.class);

    private ParameterValidator parameterValidator = ValidatorFactory.getInstance().getParameterValidator();
    private QuestionValidator questionValidator = ValidatorFactory.getInstance().getQuestionValidator();
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
    public int getQuestionsCount() throws ServiceException {
        try {
            return questionDAO.getQuestionsCount();
        } catch (DAOException e) {
            throw new ServiceException("Cannot get total questions count.", e);
        }
    }

    @Override
    public List<Question> getQuestions(String page, String count) throws ServiceException {
        if(!parameterValidator.validateNumeric(page)) {
            throw new ServiceException("Validation error. Invalid page value '" + page + "'.");
        }
        if(!parameterValidator.validateNumeric(count)) {
            throw new ServiceException("Validation error. Invalid count value '" + count + "'.");
        }
        try {
            List<Question> result = questionDAO.findQuestions(Integer.valueOf(page) - 1, Integer.valueOf(count));
            logger.debug("Get '" + count + "' questions for page '" + page + "'.");
            return result;
        } catch (DAOException e) {
            throw new ServiceException("Cannot get questions.", e);
        }
    }

    @Override
    public Question getQuestion(String questionId) throws ServiceException {
        if(!parameterValidator.validateNumeric(questionId)) {
            throw new ServiceException("Validation error. Invalid question id='" + questionId + "'.");
        }
        int id = Integer.valueOf(questionId);
        try {
            return questionDAO.findQuestion(id);
        } catch (DAOException e) {
            throw new ServiceException("Cannot get question id='" + id + "'.", e);
        }
    }

    @Override
    public List<Question> getQuestionsByUser(String id, String page, String count) throws ServiceException {
        if(!parameterValidator.validateNumeric(id)) {
            throw new ServiceException("Validation error. Invalid user id='" + id + "'.");
        }
        if(!parameterValidator.validateNumeric(page)) {
            throw new ServiceException("Validation error. Invalid page value ='" + page + "'.");
        }
        if(!parameterValidator.validateNumeric(count)) {
            throw new ServiceException("Validation error. Invalid count value ='" + count + "'.");
        }
        try {
            return questionDAO.findQuestionsByUser(Integer.valueOf(id), Integer.valueOf(page) - 1, Integer.valueOf(count));
        } catch (DAOException e) {
            throw new ServiceException("Cannot get questions for user id='" + id + "'.", e);
        }
    }

    @Override
    public int getQuestionsCountForUser(String id) throws ServiceException {
        if(!parameterValidator.validateNumeric(id)) {
            throw new ServiceException("Validation error. Invalid value id = '" + id + "'.");
        }
        try {
            return questionDAO.getQuestionsCountByUser(Integer.valueOf(id));
        } catch (DAOException e) {
            throw new ServiceException("Cannot get total questions count.", e);
        }
    }

    @Override
    public void addQuestion(Question question) throws ServiceException {
        if(!questionValidator.validate(question)) {
            throw new ServiceException("Validation error. Invalid question.");
        }
        try {
            questionDAO.insertQuestion(question);
        } catch (DAOException e) {
            throw new ServiceException("Cannot add question.", e);
        }
    }

}
