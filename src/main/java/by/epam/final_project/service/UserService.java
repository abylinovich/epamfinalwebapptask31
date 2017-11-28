package by.epam.final_project.service;

import by.epam.final_project.entity.User;
import by.epam.final_project.exception.ServiceException;

public interface UserService {

    void init() throws ServiceException;

    User findUserByLoginAndPassword(String login, String password) throws ServiceException;

    void createNewUser(String login, String password, String firstName, String lastName, String email, int age) throws ServiceException;

}
