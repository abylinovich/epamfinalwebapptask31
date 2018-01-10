package by.epam.final_project.controller.command.constant;

public class FrontMessage {

    public static final String GET_METHOD_ERROR_MESSAGE = "Method GET is not available for this command.";
    public static final String POST_METHOD_ERROR_MESSAGE = "Method POST is not available for this command.";

    public static final String TRY_AGAIN_MESSAGE = " Please try again later or call your system administrator.";
    public static final String CANNOT_REGISTER_USER_MESSAGE = "Cannot register user." + TRY_AGAIN_MESSAGE;
    public static final String CANNOT_LOGIN_MESSAGE = "Cannot login. Please check your login and password and try again";
    public static final String USER_NOT_FOUND_MESSAGE = "Cannot login. Input data is not valid.";
    public static final String SERVER_ERROR_MESSAGE = "Server is not working correctly." + TRY_AGAIN_MESSAGE;

    public static final String QUESTIONS_NOT_FOUND_MESSAGE = "No questions found.";

    private FrontMessage() {
    }
}
