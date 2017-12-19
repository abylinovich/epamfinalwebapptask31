package by.epam.final_project.service;

import by.epam.final_project.entity.User;
import by.epam.final_project.service.exception.ServiceException;

public interface UserService {

    User findUserByLoginAndPassword(String login, String password) throws ServiceException;

    User createNewUser(String login, String password, String firstName, String lastName, String email, int age) throws ServiceException;

}
