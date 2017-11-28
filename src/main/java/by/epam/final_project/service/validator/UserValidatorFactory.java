package by.epam.final_project.service.validator;

import by.epam.final_project.service.validator.impl.UserValidatorImpl;

public class UserValidatorFactory {

    private static final UserValidatorFactory userValidatorFactory = new UserValidatorFactory();
    private final UserValidator userValidator = new UserValidatorImpl();

    private UserValidatorFactory() {
    }

    public static UserValidatorFactory getInstance() {
        return userValidatorFactory;
    }

    public UserValidator getUserValidator() {
        return userValidator;
    }

}
