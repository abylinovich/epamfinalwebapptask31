package by.epam.final_project.dao.impl;

import by.epam.final_project.dao.UserDAO;
import by.epam.final_project.entity.User;

public class UserDAOImpl implements UserDAO {

    @Override
    public User findUser(String login, String password) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setAge(55);
        user.setEmail("email@gmail.com");
        user.setFirstName("Ivan");
        user.setLastName("Petrov");
        return user;
    }

}
