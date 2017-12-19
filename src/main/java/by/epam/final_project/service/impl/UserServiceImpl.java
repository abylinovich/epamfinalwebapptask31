package by.epam.final_project.service.impl;

import by.epam.final_project.dao.UserDAO;
import by.epam.final_project.dao.UserDAOFactory;
import by.epam.final_project.entity.User;
import by.epam.final_project.dao.exception.DAOException;
import by.epam.final_project.service.exception.ServiceException;
import by.epam.final_project.service.UserService;
import by.epam.final_project.service.validator.UserValidator;
import by.epam.final_project.service.validator.UserValidatorFactory;
import org.apache.log4j.Logger;


public class UserServiceImpl implements UserService {

    private final static Logger logger = Logger.getLogger(UserServiceImpl.class);

    private UserDAO userDAO = UserDAOFactory.getInstance().getUserDAO();
    private UserValidator userValidator = UserValidatorFactory.getInstance().getUserValidator();

    @Override
    public User findUserByLoginAndPassword(String login, String password) throws ServiceException {
        if(!userValidator.validateLogin(login) || !userValidator.validatePassword(password)) {
            logger.debug("Validation error.");
            throw new ServiceException("Validation error");
        }
        try {
            return userDAO.findUserByLoginAndPassword(login, password);
        } catch (DAOException e) {
            logger.error("Cannot find user.", e);
            throw new ServiceException("Cannot find user.", e);
        }
    }

    @Override
    public User createNewUser(String login, String password, String firstName, String lastName, String email, int age) throws ServiceException {
        if(!userValidator.validateLogin(login) || !userValidator.validatePassword(password)) {
            return null;
        }
        if(!userValidator.validateFirstName(firstName) ||
                !userValidator.validateLastName(lastName) ||
                !userValidator.validateEmail(email) ||
                !userValidator.validateAge(age)) {
            return null;
        }
        try {
            userDAO.createNewUser(login, password, firstName, lastName, email, age);
            return findUserByLoginAndPassword(login, password);
        } catch (DAOException e) {
            throw new ServiceException("Cannot create user.", e);
        }
    }

}
