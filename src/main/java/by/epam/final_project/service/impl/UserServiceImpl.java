package by.epam.final_project.service.impl;

import by.epam.final_project.dao.UserDAO;
import by.epam.final_project.dao.UserDAOFactory;
import by.epam.final_project.entity.User;
import by.epam.final_project.exception.DAOException;
import by.epam.final_project.exception.ServiceException;
import by.epam.final_project.service.UserService;
import by.epam.final_project.service.validator.UserValidator;
import by.epam.final_project.service.validator.UserValidatorFactory;

import static by.epam.final_project.exception.message.DAOExceptionMessageUtil.CANNOT_INITIALIZE_DAO;
import static by.epam.final_project.exception.message.ServiceExceptionMessageUtil.CANNOT_CREATE_USER_MESSAGE;
import static by.epam.final_project.exception.message.ServiceExceptionMessageUtil.CANNOT_FIND_USER_MESSAGE;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO = UserDAOFactory.getInstance().getUserDAO();
    private UserValidator userValidator = UserValidatorFactory.getInstance().getUserValidator();

    @Override
    public void init() throws ServiceException {
        try {
            userDAO.init();
        } catch (DAOException e) {
            throw new ServiceException(CANNOT_INITIALIZE_DAO, e);
        }
    }

    @Override
    public User findUserByLoginAndPassword(String login, String password) throws ServiceException {
        try {
            return userDAO.findUserByLoginAndPassword(login, password);
        } catch (DAOException e) {
            throw new ServiceException(CANNOT_FIND_USER_MESSAGE, e);
        }
    }

    @Override
    public User createNewUser(String login, String password, String firstName, String lastName, String email, int age) throws ServiceException {
        try {
            userDAO.createNewUser(login, password, firstName, lastName, email, age);
            return findUserByLoginAndPassword(login, password);
        } catch (DAOException e) {
            throw new ServiceException(CANNOT_CREATE_USER_MESSAGE, e);
        }
    }

}
