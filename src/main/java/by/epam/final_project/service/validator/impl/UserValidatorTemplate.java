package by.epam.final_project.service.validator.impl;

import by.epam.final_project.entity.User;
import by.epam.final_project.service.validator.UserValidator;

public class UserValidatorTemplate implements UserValidator {

    @Override
    public boolean validate(User user) {
        return true;
    }

    @Override
    public boolean validateLogin(String login) {
        return true;
    }

    @Override
    public boolean validatePassword(String password) {
        return true;
    }

}
