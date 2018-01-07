package by.epam.final_project.service.validator;

import by.epam.final_project.entity.User;

public interface UserValidator {

    boolean validateUser(User user);

    boolean validateLogin(String login);

    boolean validatePassword(String password);

}
