package by.epam.final_project.service.impl;

import by.epam.final_project.dao.UserDAO;
import by.epam.final_project.dao.DAOFactory;
import by.epam.final_project.entity.User;
import by.epam.final_project.dao.exception.DAOException;
import by.epam.final_project.service.exception.ServiceException;
import by.epam.final_project.service.UserService;
import by.epam.final_project.service.validator.UserValidator;
import by.epam.final_project.service.validator.ValidatorFactory;
import org.apache.log4j.Logger;


public class UserServiceImpl implements UserService {

    private final static Logger logger = Logger.getLogger(UserServiceImpl.class);

    private UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
    private UserValidator userValidator = ValidatorFactory.getInstance().getUserValidator();

    @Override
    public User findUser(String login, String password) throws ServiceException {
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
    public void register(User user) throws ServiceException {
        if(!userValidator.validateUser(user)) {
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
