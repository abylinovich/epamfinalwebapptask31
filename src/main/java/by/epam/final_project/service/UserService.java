package by.epam.final_project.service;

import by.epam.final_project.entity.User;

public interface UserService {

    User findUserByLoginAndPassword(String login, String password);

    void createNewUser(String login, String password, String firstName, String lastName, String email, int age);

}
