package by.epam.finalproject.service.impl;

import by.epam.finalproject.dao.AnswerDAO;
import by.epam.finalproject.dao.DAOFactory;
import by.epam.finalproject.dao.exception.DAOException;
import by.epam.finalproject.entity.Answer;
import by.epam.finalproject.service.AnswerService;
import by.epam.finalproject.service.exception.ServiceException;
import by.epam.finalproject.service.validator.AnswerValidator;
import by.epam.finalproject.service.validator.ValidatorFactory;
import org.apache.log4j.Logger;

public class AnswerServiceImpl implements AnswerService {

    private final static Logger logger = Logger.getLogger(AnswerServiceImpl.class);

    private AnswerValidator answerValidator = ValidatorFactory.getInstance().getAnswerValidator();
    private AnswerDAO answerDAO = DAOFactory.getInstance().getAnswerDAO();


    @Override
    public void addAnswer(Answer answer) throws ServiceException {
        if(!answerValidator.validate(answer)) {
            throw new ServiceException("Validation error. Invalid answer.");
        }
        try {
            answerDAO.insertAnswer(answer);
            logger.debug("Answer has been successfully added.");
        } catch (DAOException e) {
            throw new ServiceException("Cannot add answer.", e);
        }
    }

}
