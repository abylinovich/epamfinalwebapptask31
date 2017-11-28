package by.epam.final_project.service.validator.impl;

import by.epam.final_project.service.validator.UserValidatorTemplate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidatorImpl extends UserValidatorTemplate {

    Pattern loginPattern = Pattern.compile("[a-zA-Z0-9_-]+");

    @Override
    public boolean validateLogin(String login) {
        if(login == null) {
            return false;
        }
        if(login.isEmpty()) {
            return false;
        }
        // TODO: 28.11.2017
        return true;
    }

    @Override
    public boolean validatePassword(String password) {
        // TODO: 28.11.2017
        return true;
    }

    @Override
    public boolean validateFirstName(String firstName) {
        // TODO: 28.11.2017
        return true;
    }

    @Override
    public boolean validateLastName(String lastName) {
        // TODO: 28.11.2017
        return true;
    }

    @Override
    public boolean validateEmail(String email) {
        // TODO: 28.11.2017
        return true;
    }

    @Override
    public boolean validateAge(int age) {
        // TODO: 28.11.2017
        return true;
    }

}
