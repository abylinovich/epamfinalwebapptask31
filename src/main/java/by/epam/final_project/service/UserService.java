package by.epam.final_project.service;

import by.epam.final_project.entity.User;
import by.epam.final_project.service.exception.ServiceException;

public interface UserService {

    User findUser(String login, String password) throws ServiceException;

    void register(User user) throws ServiceException;

    void resetPassword(User user);

}
