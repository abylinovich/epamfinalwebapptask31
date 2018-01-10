package by.epam.final_project.service.validator;

import by.epam.final_project.service.validator.impl.ParameterValidatorImpl;
import by.epam.final_project.service.validator.impl.UserValidatorImpl;

public class ValidatorFactory {

    private static final ValidatorFactory VALIDATOR_FACTORY = new ValidatorFactory();

    private final UserValidator userValidator = new UserValidatorImpl();
    private final ParameterValidator parameterValidator = new ParameterValidatorImpl();

    private ValidatorFactory() {
    }

    public static ValidatorFactory getInstance() {
        return VALIDATOR_FACTORY;
    }

    public UserValidator getUserValidator() {
        return userValidator;
    }

    public ParameterValidator getParameterValidator() {
        return parameterValidator;
    }

}
