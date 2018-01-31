package by.epam.finalproject.service.validator;

import by.epam.finalproject.entity.User;

public interface UserValidator {

    boolean validate(User user);

    boolean validateLogin(String login);

    boolean validatePassword(String password);

}
