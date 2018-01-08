package by.epam.final_project.service;

import by.epam.final_project.service.impl.QuestionServiceImpl;
import by.epam.final_project.service.impl.UserServiceImpl;

public class ServiceFactory {

    private static final ServiceFactory SERVICE_FACTORY = new ServiceFactory();

    private final UserService userService = new UserServiceImpl();
    private final QuestionService questionService = new QuestionServiceImpl();

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

}
