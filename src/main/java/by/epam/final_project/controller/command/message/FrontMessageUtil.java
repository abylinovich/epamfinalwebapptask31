package by.epam.final_project.controller.command.message;

public class FrontMessageUtil {

    public static final String TRY_AGAIN_MESSAGE = " Please try again later or call your system administrator.";
    public static final String CANNOT_REGISTER_USER_MESSAGE = "Cannot register user." + TRY_AGAIN_MESSAGE;
    public static final String CANNOT_LOGIN_MESSAGE = "Cannot login. Please check your login and password and try again";
    public static final String SERVER_ERROR_MESSAGE = "Server is not working correctly." + TRY_AGAIN_MESSAGE;

    private FrontMessageUtil() {
    }
}