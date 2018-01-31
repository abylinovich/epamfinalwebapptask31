package by.epam.finalproject.dao.constant;

public class DatabaseTable {

    public class Questions {
        public static final String QUESTION_ID = "question_id";
        public static final String QUESTION_TITLE = "question_title";
        public static final String QUESTION = "question";
        public static final String THEME_ID = "theme_id";
        public static final String USER_ID = "user_id";
    }

    public class Answers {
        public static final String ANSWER_ID = "answer_id";
        public static final String ANSWER = "answer";
        public static final String QUESTION_ID = "question_id";
        public static final String USER_ID = "user_id";
    }

    public class Themes {
        public static final String THEME_ID = "theme_id";
        public static final String LOCALE_ID = "locale_id";
        public static final String THEME_TITLE = "theme_title";
    }

    public class Users {
        public static final String USER_ID = "user_id";
        public static final String ROLE = "role";
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
        public static final String FIRST_NAME = "first_name";
        public static final String LAST_NAME = "last_name";
        public static final String EMAIL = "email";
        public static final String LOCALE_ID = "locale_id";
    }

    public class Locales {
        public static final String LOCALE_ID = "locale_id";
        public static final String LOCALE_TITLE = "locale_title";
        public static final String LANGUAGE = "language";
        public static final String COUNTRY = "country";
    }

    private DatabaseTable() {}

}
