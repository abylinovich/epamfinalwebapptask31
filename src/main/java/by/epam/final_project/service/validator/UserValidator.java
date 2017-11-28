package by.epam.final_project.service.validator;

public interface UserValidator {

    boolean validateLogin(String login);

    boolean validatePassword(String password);

    boolean validateFirstName(String firstName);

    boolean validateLastName(String lastName);

    boolean validateEmail(String email);

    boolean validateAge(int age);

}
