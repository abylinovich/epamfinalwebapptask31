package by.epam.finalproject.service;

import by.epam.finalproject.entity.User;
import by.epam.finalproject.service.exception.ServiceException;

public interface UserService {

    User findUser(String login, String password) throws ServiceException;

    void register(User user) throws ServiceException;

    void resetPassword(User user);

}
