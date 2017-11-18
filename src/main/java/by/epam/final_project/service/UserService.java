package by.epam.final_project.service;

import by.epam.final_project.entity.User;

public interface UserService {

    User findUser(String login, String password);

}
