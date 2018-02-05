package by.epam.finalproject.service.validator.impl;

import by.epam.finalproject.entity.Question;
import by.epam.finalproject.service.validator.InternalValidatorFactory;
import by.epam.finalproject.service.validator.QuestionValidator;
import by.epam.finalproject.service.validator.ThemeValidator;
import org.apache.log4j.Logger;

import java.util.regex.Pattern;


public class QuestionValidatorImpl implements QuestionValidator {

    private final static Logger logger = Logger.getLogger(QuestionValidatorImpl.class);

    private ThemeValidator themeValidator = InternalValidatorFactory.getInstance().getThemeValidator();


    private static final String TITLE_REGEX = "[\u0020-\u007E\u0400-\u04FF]{1,50}";
    private static final String QUESTION_REGEX = "[\u0020-\u007E\u0400-\u04FF]{1,255}";

    private Pattern titlePattern = Pattern.compile(TITLE_REGEX, Pattern.UNICODE_CHARACTER_CLASS);
    private Pattern questionDataPattern = Pattern.compile(QUESTION_REGEX, Pattern.UNICODE_CHARACTER_CLASS);


    @Override
    public boolean validate(Question question) {
        if(question == null) {
            return false;
        }
        String title = question.getTitle();
        if(!validateTitle(title)) {
            return false;
        }

        String data = question.getQuestion();
        if(!validateQuestionData(data)) {
            return false;
        }

        if(!themeValidator.validate(question.getTheme())) {
            return false;
        }

        if(question.getUser() == null) {
            logger.debug("Validation error. User is null.");
            return false;
        }
        return true;
    }

    @Override
    public boolean validateQuestionEdit(Question question) {
        if(question == null) {
            logger.debug("Validation error. Question is null.");
            return false;
        }
        String title = question.getTitle();
        if(!validateTitle(title)) {
            return false;
        }

        String data = question.getQuestion();
        if(!validateTitle(data)) {
            return false;
        }
        return true;
    }

    private boolean validateTitle(String title) {
        if(title == null || !titlePattern.matcher(title).matches()) {
            logger.debug("Validation error. Question title has wrong value - '" + title + "'.");
            return false;
        }
        return true;
    }

    private boolean validateQuestionData(String data) {
        if(data == null || !questionDataPattern.matcher(data).matches()) {
            logger.debug("Validation error. Question has wrong value - '" + data + "'.");
            return false;
        }
        return true;
    }

}
