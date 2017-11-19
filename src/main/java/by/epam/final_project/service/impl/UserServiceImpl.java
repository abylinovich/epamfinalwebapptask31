package by.epam.final_project.service.impl;

import by.epam.final_project.dao.UserDAO;
import by.epam.final_project.dao.UserDAOFactory;
import by.epam.final_project.entity.User;
import by.epam.final_project.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO = UserDAOFactory.getUserDAO();

    @Override
    public User findUserByLoginAndPassword(String login, String password) {
        return userDAO.findUserByLoginAndPassword(login, password);
    }

    @Override
    public void createNewUser(String login, String password, String firstName, String lastName, String email, int age) {
        userDAO.createNewUser(login, password, firstName, lastName, email, age);
    }

}
