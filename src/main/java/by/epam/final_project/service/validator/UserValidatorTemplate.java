package by.epam.final_project.service.validator;

public class UserValidatorTemplate implements UserValidator {

    @Override
    public boolean validateLogin(String login) {
        return true;
    }

    @Override
    public boolean validatePassword(String password) {
        return true;
    }

    @Override
    public boolean validateFirstName(String firstName) {
        return true;
    }

    @Override
    public boolean validateLastName(String lastName) {
        return true;
    }

    @Override
    public boolean validateEmail(String email) {
        return true;
    }

    @Override
    public boolean validateAge(int age) {
        return true;
    }
}
