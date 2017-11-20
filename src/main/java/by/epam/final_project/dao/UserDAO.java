package by.epam.final_project.dao;

import by.epam.final_project.entity.User;
import by.epam.final_project.exception.DAOException;

public interface UserDAO {

    User findUserByLoginAndPassword(String login, String password) throws DAOException;

    void createNewUser(String login, String password, String firstName, String lastName, String email, int age) throws DAOException;

}
