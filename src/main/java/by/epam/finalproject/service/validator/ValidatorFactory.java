package by.epam.finalproject.service.validator;

import by.epam.finalproject.service.validator.impl.AnswerValidatorImpl;
import by.epam.finalproject.service.validator.impl.ParameterValidatorImpl;
import by.epam.finalproject.service.validator.impl.QuestionValidatorImpl;
import by.epam.finalproject.service.validator.impl.UserValidatorImpl;

public class ValidatorFactory {

    private static final ValidatorFactory VALIDATOR_FACTORY = new ValidatorFactory();

    private final UserValidator userValidator = new UserValidatorImpl();
    private final ParameterValidator parameterValidator = new ParameterValidatorImpl();
    private final QuestionValidator questionValidator = new QuestionValidatorImpl();
    private final AnswerValidator answerValidator = new AnswerValidatorImpl();


    private ValidatorFactory() {
    }

    public static ValidatorFactory getInstance() {
        return VALIDATOR_FACTORY;
    }

    public UserValidator getUserValidator() {
        return userValidator;
    }

    public ParameterValidator getParameterValidator() {
        return parameterValidator;
    }

    public QuestionValidator getQuestionValidator() { return questionValidator; }

    public AnswerValidator getAnswerValidator() {
        return answerValidator;
    }

}
