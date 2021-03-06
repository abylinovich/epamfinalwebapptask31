package by.epam.finalproject.service;

import by.epam.finalproject.service.impl.AnswerServiceImpl;
import by.epam.finalproject.service.impl.QuestionServiceImpl;
import by.epam.finalproject.service.impl.ThemeServiceImpl;
import by.epam.finalproject.service.impl.UserServiceImpl;


public class ServiceFactory {

    private static final ServiceFactory SERVICE_FACTORY = new ServiceFactory();

    private final UserService userService = new UserServiceImpl();
    private final QuestionService questionService = new QuestionServiceImpl();
    private final ThemeService themeService = new ThemeServiceImpl();
    private final AnswerService answerService = new AnswerServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return SERVICE_FACTORY;
    }

    public UserService getUserService() {
        return userService;
    }

    public QuestionService getQuestionService() {
        return questionService;
    }

    public ThemeService getThemeService() {
        return themeService;
    }

    public AnswerService getAnswerService() {
        return answerService;
    }

}
