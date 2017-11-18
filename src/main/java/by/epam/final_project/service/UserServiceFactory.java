package by.epam.final_project.service;

import by.epam.final_project.service.impl.UserServiceImpl;

public class UserServiceFactory {

    private static final UserServiceFactory userServiceFactory = new UserServiceFactory();
    private static final UserService userService = new UserServiceImpl();

    private UserServiceFactory() {

    }

    public static UserServiceFactory getInstance() {
        return userServiceFactory;
    }

    public static UserService getUserService() {
        return userService;
    }

}
