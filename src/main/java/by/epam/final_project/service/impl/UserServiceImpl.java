package by.epam.final_project.service.impl;

import by.epam.final_project.dao.UserDAOFactory;
import by.epam.final_project.entity.User;
import by.epam.final_project.exception.DAOException;
import by.epam.final_project.exception.ServiceException;
import by.epam.final_project.service.UserService;

import static by.epam.final_project.exception.message.ServiceExceptionMessageUtil.CANNOT_CREATE_USER_MESSAGE;
import static by.epam.final_project.exception.message.ServiceExceptionMessageUtil.CANNOT_FIND_USER_MESSAGE;

public class UserServiceImpl implements UserService {

    private UserDAOFactory userDAOFactory = UserDAOFactory.getInstance();

    @Override
    public User findUserByLoginAndPassword(String login, String password) throws ServiceException {
        try {
            return userDAOFactory.getUserDAO().findUserByLoginAndPassword(login, password);
        } catch (DAOException e) {
            throw new ServiceException(CANNOT_FIND_USER_MESSAGE, e);
        }
    }

    @Override
    public void createNewUser(String login, String password, String firstName, String lastName, String email, int age) throws ServiceException {
        try {
            userDAOFactory.getUserDAO().createNewUser(login, password, firstName, lastName, email, age);
        } catch (DAOException e) {
            throw new ServiceException(CANNOT_CREATE_USER_MESSAGE, e);
        }
    }

}
