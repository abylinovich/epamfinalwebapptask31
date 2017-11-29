package by.epam.final_project.service.validator.impl;

import by.epam.final_project.service.validator.UserValidatorTemplate;

import java.util.regex.Pattern;

public class UserValidatorImpl extends UserValidatorTemplate {

    private static final String LOGIN_PASSWORD_REGEX = "\\w+";
    private static final String NAME_REGEX_EN = "[a-zA-Z]+";
    private static final String NAME_REGEX_RU = "[а-яА-Я]+";
    private static final String EMAIL_REGEX = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";

    private Pattern loginPasswordPattern = Pattern.compile(LOGIN_PASSWORD_REGEX);
    private Pattern nameRuPattern = Pattern.compile(NAME_REGEX_RU);
    private Pattern nameEnPattern = Pattern.compile(NAME_REGEX_EN);
    private Pattern emailPattern = Pattern.compile(EMAIL_REGEX);

    @Override
    public boolean validateLogin(String login) {
        if(checkForNullAndEmpty(login)) {
            return false;
        }
        if(!loginPasswordPattern.matcher(login).matches()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean validatePassword(String password) {
        if(checkForNullAndEmpty(password)) {
            return false;
        }
        if(!loginPasswordPattern.matcher(password).matches()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean validateFirstName(String firstName) {
        if(checkForNullAndEmpty(firstName)) {
            return false;
        }
        if(!nameEnPattern.matcher(firstName).matches() || !nameRuPattern.matcher(firstName).matches()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean validateLastName(String lastName) {
        if(checkForNullAndEmpty(lastName)) {
            return false;
        }
        if(!nameEnPattern.matcher(lastName).matches() || !nameRuPattern.matcher(lastName).matches()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean validateEmail(String email) {
        if(checkForNullAndEmpty(email)) {
            return false;
        }
        if(!emailPattern.matcher(email).matches()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean validateAge(int age) {
        if(age < 0 || age > 100) {
            return false;
        }
        return true;
    }

    private boolean checkForNullAndEmpty(String input) {
        if(input == null || input.isEmpty()) {
            return true;
        }
        return false;
    }

}
