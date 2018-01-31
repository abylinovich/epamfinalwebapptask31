package by.epam.finalproject.dao;

import by.epam.finalproject.dao.impl.AnswerDAOImpl;
import by.epam.finalproject.dao.impl.QuestionDAOImpl;
import by.epam.finalproject.dao.impl.ThemeDAOImpl;
import by.epam.finalproject.dao.impl.UserDAOImpl;

public class DAOFactory {

    private static final DAOFactory DAO_FACTORY = new DAOFactory();

    private final UserDAO userDAO = new UserDAOImpl();
    private final QuestionDAO questionDAO = new QuestionDAOImpl();
    private final ThemeDAO themeDAO = new ThemeDAOImpl();
    private final AnswerDAO answerDAO = new AnswerDAOImpl();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return DAO_FACTORY;
    }

    public UserDAO getUserDAO() {
            return userDAO;
    }

    public QuestionDAO getQuestionDAO() {
        return questionDAO;
    }

    public ThemeDAO getThemeDAO() { return themeDAO; }

    public AnswerDAO getAnswerDAO() {
        return answerDAO;
    }

}
