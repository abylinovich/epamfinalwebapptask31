package by.epam.final_project.dao;

import by.epam.final_project.dao.impl.UserDAOImpl;

public class UserDAOFactory {


    private static final UserDAOFactory userDAOFactory = new UserDAOFactory();
    private final UserDAO userDAO = new UserDAOImpl();

    private UserDAOFactory() {
    }

    public static UserDAOFactory getInstance() {
        return userDAOFactory;
    }

    public UserDAO getUserDAO() {
            return userDAO;
    }

}
