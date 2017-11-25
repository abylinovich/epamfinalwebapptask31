package by.epam.final_project.dao;

import by.epam.final_project.dao.impl.UserDAOImpl;
import by.epam.final_project.exception.DAOException;

import static by.epam.final_project.exception.message.DAOExceptionMessageUtil.DATABASE_DRIVER_LOAD_EXCEPTION_MESSAGE;

public class UserDAOFactory {

    // TODO: 23.11.2017 deprecated?
    private static final String DATABASE_DRIVER_CLASS_PATH = "com.mysql.jdbc.Driver";

    private static final UserDAOFactory userDAOFactory = new UserDAOFactory();
    private static UserDAO userDAO;

    static {
        try {
            Class.forName(DATABASE_DRIVER_CLASS_PATH);
            userDAO = new UserDAOImpl();
        } catch (ClassNotFoundException e) {
            userDAO = null;
        }
    }

    private UserDAOFactory() {
    }

    public static UserDAOFactory getInstance() {
        return userDAOFactory;
    }

    public UserDAO getUserDAO() throws DAOException {
        if(userDAO != null) {
            return userDAO;
        } else {
            throw new DAOException(DATABASE_DRIVER_LOAD_EXCEPTION_MESSAGE);
        }
    }

}
