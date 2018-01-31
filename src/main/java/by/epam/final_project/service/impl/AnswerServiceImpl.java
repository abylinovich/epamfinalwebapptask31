package by.epam.final_project.service.impl;

import by.epam.final_project.dao.AnswerDAO;
import by.epam.final_project.dao.DAOFactory;
import by.epam.final_project.dao.exception.DAOException;
import by.epam.final_project.entity.Answer;
import by.epam.final_project.service.AnswerService;
import by.epam.final_project.service.exception.ServiceException;
import by.epam.final_project.service.validator.AnswerValidator;
import by.epam.final_project.service.validator.ValidatorFactory;
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
        } catch (DAOException e) {
            throw new ServiceException("Cannot add answer.", e);
        }
    }

}
