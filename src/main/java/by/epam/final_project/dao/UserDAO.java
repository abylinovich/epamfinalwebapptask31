package by.epam.final_project.dao;

import by.epam.final_project.entity.User;

public interface UserDAO {

    User findUser(String login, String password);

}
