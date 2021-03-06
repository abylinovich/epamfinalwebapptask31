package by.epam.finalproject.service.validator.impl;

import by.epam.finalproject.entity.User;
import by.epam.finalproject.entity.UserRole;
import org.apache.log4j.Logger;

import java.util.Locale;
import java.util.regex.Pattern;


public class UserValidatorImpl extends UserValidatorTemplate {

    private final static Logger logger = Logger.getLogger(UserValidatorImpl.class);

    private static final String USERNAME_REGEX = "[\\d\\u0041-\\u007E\\u0061-\\u007A\\u0410-\\u044F]{3,15}";
    private static final String TEXT_REGEX = "[\\u0041-\\u007E\\u0061-\\u007A\\u0410-\\u044F]+";
    private static final String EMAIL_REGEX =
            "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"
            ;

    private Pattern usernamePattern = Pattern.compile(USERNAME_REGEX, Pattern.UNICODE_CHARACTER_CLASS);
    private Pattern textPattern = Pattern.compile(TEXT_REGEX, Pattern.UNICODE_CHARACTER_CLASS);
    private Pattern emailPattern = Pattern.compile(EMAIL_REGEX);


    @Override
    public boolean validate(User user) {
        if(user == null) {
            logger.debug("Validation error. User is null.");
            return false;
        }
        UserRole role = user.getRole();
        if(!validateRole(role)) {
            logger.debug("Validation error. Role is null.");
            return false;
        }
        String login = user.getUsername();
        if(!validateLogin(login)) {
            logger.debug("Validation error. Invalid login for user '" + login + "'.");
            return false;
        }
        String password = user.getPassword();
        if(!validatePassword(password)) {
            logger.debug("Validation error. Invalid password for user '" + login + "'.");
            return false;
        }
        String firstName = user.getFirstName();
        if(!validateFirstName(firstName)) {
            logger.debug("Validation error. Invalid first name '" + firstName + "' for user '" + login + "'.");
            return false;
        }
        String lastName = user.getLastName();
        if(!validateLastName(lastName)) {
            logger.debug("Validation error. Invalid last name '" + lastName + "' for user '" + login + "'.");
            return false;
        }
        String email = user.getEmail();
        if(!validateEmail(email)) {
            logger.debug("Validation error. Invalid email '" + email + "' for user '" + login + "'.");
            return false;
        }
        int age = user.getAge();
        if(!validateAge(age)) {
            logger.debug("Validation error. Invalid age '" + age + "' for user '" + login + "'.");
            return false;
        }
        Locale locale = user.getLocale();
        if(!validateLocale(locale)) {
            logger.debug("Validation error. Locale is null.");
            return false;
        }
        logger.debug("User validation successful for user '" + login + "'.");
        return true;
    }

    @Override
    public boolean validateLogin(String login) {
        if(checkForNullAndEmpty(login)) {
            logger.debug("Validation error. Login is null or empty.");
            return false;
        }
        if(!usernamePattern.matcher(login).matches()) {
            logger.debug("Validation error. Login regex mismatch.");
            return false;
        }
        logger.debug("Login validation successful.");
        return true;
    }

    @Override
    public boolean validatePassword(String password) {
        if(checkForNullAndEmpty(password)) {
            logger.debug("Validation error. Password is null or empty.");
            return false;
        }
        if(!usernamePattern.matcher(password).matches()) {
            logger.debug("Validation error. Password regex mismatch.");
            return false;
        }
        logger.debug("Password validation successful.");
        return true;
    }

    private boolean validateRole(UserRole role) {
        if(role == null) {
            logger.debug("Validation error. Role is null.");
            return false;
        }
        return true;
    }

    private boolean validateFirstName(String firstName) {
        if(checkForNullAndEmpty(firstName)) {
            return false;
        }
        if(!textPattern.matcher(firstName).matches()) {
            return false;
        }
        return true;
    }

    private boolean validateLastName(String lastName) {
        if(checkForNullAndEmpty(lastName)) {
            return false;
        }
        if(!textPattern.matcher(lastName).matches()) {
            return false;
        }
        return true;
    }

    private boolean validateEmail(String email) {
        if(checkForNullAndEmpty(email)) {
            return false;
        }
        if(!emailPattern.matcher(email).matches()) {
            return false;
        }
        return true;
    }

    private boolean validateAge(int age) {
        if(age < 0 || age > 100) {
            return false;
        }
        return true;
    }

    private boolean validateLocale(Locale locale) {
        if(locale == null) {
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
