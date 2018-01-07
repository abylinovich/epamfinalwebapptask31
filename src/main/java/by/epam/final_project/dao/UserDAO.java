package by.epam.final_project.dao;

import by.epam.final_project.entity.User;
import by.epam.final_project.dao.exception.DAOException;

public interface UserDAO {

    User findUser(String login, String password) throws DAOException;

    void register(User user) throws DAOException;

}
