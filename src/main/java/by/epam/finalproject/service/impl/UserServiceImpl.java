package by.epam.finalproject.service.impl;

import by.epam.finalproject.dao.UserDAO;
import by.epam.finalproject.dao.DAOFactory;
import by.epam.finalproject.entity.User;
import by.epam.finalproject.dao.exception.DAOException;
import by.epam.finalproject.service.exception.ServiceException;
import by.epam.finalproject.service.UserService;
import by.epam.finalproject.service.validator.ParameterValidator;
import by.epam.finalproject.service.validator.UserValidator;
import by.epam.finalproject.service.validator.ValidatorFactory;
import org.apache.log4j.Logger;


public class UserServiceImpl implements UserService {

    private final static Logger logger = Logger.getLogger(UserServiceImpl.class);

    private UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
    private UserValidator userValidator = ValidatorFactory.getInstance().getUserValidator();
    private ParameterValidator parameterValidator = ValidatorFactory.getInstance().getParameterValidator();

    @Override
    public User getUser(String login, String password) throws ServiceException {
        if(!userValidator.validateLogin(login) || !userValidator.validatePassword(password)) {
            throw new ServiceException("Validation failed.");
        }
        try {
            return userDAO.findUser(login, password);
        } catch (DAOException e) {
            throw new ServiceException("Cannot find user.", e);
        }
    }

    @Override
    public User getUserByQuestionId(String id) throws ServiceException {
        if(!parameterValidator.validateNumeric(id)) {
            throw new ServiceException("Validation error. Invalid question id='" + id + "'.");
        }
        try {
            return userDAO.findUserByQuestionId(Integer.valueOf(id));
        } catch (DAOException e) {
            throw new ServiceException("Cannot find user by question id='" + id + "'.", e);
        }
    }

    @Override
    public void register(User user) throws ServiceException {
        if(!userValidator.validate(user)) {
            throw new ServiceException("User was not registered. Validation error.");
        }
        try {
            userDAO.register(user);
        } catch (DAOException e) {
            throw new ServiceException("Cannot create user.", e);
        }
    }

    @Override
    public void resetPassword(User user) {
        user.setPassword(null);
    }

}
