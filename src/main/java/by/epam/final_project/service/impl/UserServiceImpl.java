package by.epam.final_project.service.impl;

import by.epam.final_project.dao.UserDAO;
import by.epam.final_project.dao.UserDAOFactory;
import by.epam.final_project.entity.User;
import by.epam.final_project.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO = UserDAOFactory.getUserDAO();

    @Override
    public User findUser(String login, String password) {
        return userDAO.findUser(login, password);
    }

}
