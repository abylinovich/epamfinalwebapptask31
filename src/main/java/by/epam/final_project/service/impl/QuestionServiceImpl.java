package by.epam.final_project.service.impl;

import by.epam.final_project.dao.DAOFactory;
import by.epam.final_project.dao.QuestionDAO;
import by.epam.final_project.dao.exception.DAOException;
import by.epam.final_project.entity.Question;
import by.epam.final_project.service.QuestionService;
import by.epam.final_project.service.exception.ServiceException;
import org.apache.log4j.Logger;

public class QuestionServiceImpl implements QuestionService {

    private final static Logger logger = Logger.getLogger(QuestionServiceImpl.class);

    private QuestionDAO questionDAO = DAOFactory.getInstance().getQuestionDAO();

    @Override
    public Question getRandomQuestion() throws ServiceException {
        try {
            return questionDAO.findRandomQuestion();
        } catch (DAOException e) {
            throw new ServiceException("Cannot get random question.", e);
        }
    }

}
