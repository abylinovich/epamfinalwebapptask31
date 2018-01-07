package by.epam.final_project.service.validator;

import by.epam.final_project.entity.User;

public class UserValidatorTemplate implements UserValidator {

    @Override
    public boolean validateUser(User user) {
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
