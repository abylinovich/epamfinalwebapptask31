package by.epam.finalproject.dao;

import by.epam.finalproject.entity.User;
import by.epam.finalproject.dao.exception.DAOException;

public interface UserDAO {

    User findUser(String login, String password) throws DAOException;

    void register(User user) throws DAOException;

}
