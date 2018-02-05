package by.epam.finalproject.service.validator.impl;

import by.epam.finalproject.entity.User;
import by.epam.finalproject.service.validator.UserValidator;


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
