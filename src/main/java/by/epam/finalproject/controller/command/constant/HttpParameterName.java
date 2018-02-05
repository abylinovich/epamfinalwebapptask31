package by.epam.finalproject.controller.command.constant;

public class HttpParameterName {

    public static final String COMMAND_PARAMETER_NAME = "command";
    public static final String LOGIN_COMMAND_NAME = "login";
    public static final String REGISTER_COMMAND_NAME = "register";
    public static final String LOGOUT_COMMAND_NAME = "logout";
    public static final String QUESTION_COMMAND_NAME = "question";
    public static final String ANSWER_COMMAND_NAME = "answer";
    public static final String USER_COMMAND_NAME = "user";


    public static final String LANGUAGE_PARAMETER_NAME = "language";

    public static final String REFERER_HEADER_NAME = "Referer";

    public static final String ID_PARAMETER_NAME = "id";
    public static final String LOGIN_PARAMETER_NAME = "login";
    public static final String REGISTER_PARAMETER_NAME = "register";
    public static final String USER_PARAMETER_NAME = "user";
    public static final String USERNAME_PARAMETER_NAME = "username";
    public static final String PASSWORD_PARAMETER_NAME = "password";
    public static final String FIRST_NAME_PARAMETER_NAME = "firstname";
    public static final String LAST_NAME_PARAMETER_NAME = "lastname";
    public static final String EMAIL_PARAMETER_NAME = "email";
    public static final String AGE_PARAMETER_NAME = "age";
    public static final String LOCALE_PARAMETER_NAME = "userLocale";

    public static final String QUESTION_TITLE_PARAMETER_NAME = "title";
    public static final String QUESTION_ID_PARAMETER_NAME = "questionId";
    public static final String QUESTION_DATA_PARAMETER_NAME = "question";
    public static final String QUESTION_THEME_PARAMETER_NAME = "theme";

    public static final String DO_PARAMETER_NAME = "do";
    public static final String ALL_DO_PARAMETER_NAME = "all";
    public static final String MY_DO_PARAMETER_NAME = "my";
    public static final String GET_DO_PARAMETER_NAME = "get";
    public static final String ASK_DO_PARAMETER_NAME = "ask";

    public static final String CREATE_DO_PARAMETER_NAME = "create";
    public static final String EDIT_DO_PARAMETER_NAME = "edit";
    public static final String DELETE_DO_PARAMETER_NAME = "delete";

    public static final String QUESTIONS_PARAMETER_NAME = "questions";
    public static final String QUESTION_PARAMETER_NAME = "question";
    public static final String DELETE_SUCCESS_PARAMETER_NAME = "deleteSuccess";
    public static final String ANSWER_PARAMETER_NAME = "answer";
    public static final String THEMES_PARAMETER_NAME = "themes";
    public static final String INPUT_PARAMETER_NAME = "input";
    public static final String RANDOM_QUESTION_PARAMETER_NAME = "random";
    public static final String PAGE_PARAMETER_NAME = "page";
    public static final String COUNT_PARAMETER_NAME = "count";


    public static final String TOTAL_QUESTIONS_ATTRIBUTE_NAME = "totalQuestions";
    public static final String QUERY_ATTRIBUTE_NAME = "query";
    public static final String PROCESSED_ATTRIBUTE_NAME = "processed";


    public static final String BAD_REQUEST_PARAMETER_ERROR_ATTRIBUTE_NAME = "badRequest";
    public static final String SERVER_ERROR_ATTRIBUTE_NAME = "serverError";

    public static final String MESSAGE_ERROR_ATTRIBUTE_NAME = "generalError";
    public static final String METHOD_ERROR_ATTRIBUTE_NAME = "methodError";

    public static final String NO_SUCH_USER_ERROR_ATTRIBUTE_NAME = "noSuchUserError";
    public static final String CANNOT_LOGIN_ERROR_ATTRIBUTE_NAME = "cannotLoginError";
    public static final String CANNOT_REGISTER_ERROR_ATTRIBUTE_NAME = "cannotRegisterError";
    public static final String ADD_QUESTION_ERROR_ATTRIBUTE_NAME = "addQuestionError";
    public static final String ADD_ANSWER_ERROR_ATTRIBUTE_NAME = "addAnswerError";
    public static final String DELETE_QUESTION_ERROR_ATTRIBUTE_NAME = "deleteQuestionError";


    private HttpParameterName() {
    }
}
