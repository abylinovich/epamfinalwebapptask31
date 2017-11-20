package by.epam.final_project.service.impl;

import by.epam.final_project.dao.UserDAO;
import by.epam.final_project.dao.UserDAOFactory;
import by.epam.final_project.entity.User;
import by.epam.final_project.exception.DAOException;
import by.epam.final_project.exception.ServiceException;
import by.epam.final_project.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO = UserDAOFactory.getUserDAO();

    @Override
    public User findUserByLoginAndPassword(String login, String password) throws ServiceException {
        try {
            return userDAO.findUserByLoginAndPassword(login, password);
        } catch (DAOException e) {
            throw new ServiceException("No user found");
        }
    }

    @Override
    public void createNewUser(String login, String password, String firstName, String lastName, String email, int age) throws ServiceException {
        try {
            userDAO.createNewUser(login, password, firstName, lastName, email, age);
        } catch (DAOException e) {
            throw new ServiceException("Cannot create new user");
        }
    }

}
