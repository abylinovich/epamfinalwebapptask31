package by.epam.final_project.dao;

import by.epam.final_project.dao.impl.UserDAOImpl;

public class UserDAOFactory {

    private static final UserDAOFactory userDAOFactory = new UserDAOFactory();
    private static final UserDAO userDAO = new UserDAOImpl();

    private UserDAOFactory() {

    }

    public static UserDAOFactory getInstance() {
        return userDAOFactory;
    }

    public static UserDAO getUserDAO() {
        return userDAO;
    }

}
