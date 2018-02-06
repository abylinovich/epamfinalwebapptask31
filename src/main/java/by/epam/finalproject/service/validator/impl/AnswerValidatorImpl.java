package by.epam.finalproject.service.validator.impl;

import by.epam.finalproject.entity.Answer;
import by.epam.finalproject.service.validator.AnswerValidator;
import org.apache.log4j.Logger;

import java.util.regex.Pattern;


public class AnswerValidatorImpl implements AnswerValidator {

    private final static Logger logger = Logger.getLogger(AnswerValidatorImpl.class);

    private static final String ANSWER_REGEX = "[\\u0020-\\u007E\\u0410-\\u044F]{1,255}";

    private Pattern answerPattern = Pattern.compile(ANSWER_REGEX, Pattern.UNICODE_CHARACTER_CLASS);


    @Override
    public boolean validate(Answer answer) {
        if(answer == null) {
            logger.debug("Validation error. Answer is null.");
            return false;
        }
        String data = answer.getAnswer();
        if(data == null || !answerPattern.matcher(data).matches()) {
            logger.debug("Validation error. Answer has wrong value - '" + data + "'.");
            return false;
        }
        if(answer.getQuestion() == null) {
            logger.debug("Validation error. Question is null.");
            return false;
        }
        if(answer.getUser() == null) {
            logger.debug("Validation error. User is null.");
            return false;
        }
        return true;
    }

}
