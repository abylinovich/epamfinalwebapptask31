package by.epam.finalproject.controller.command.impl.question.post;

import by.epam.finalproject.controller.command.impl.question.post.impl.CreateQuestionStrategy;
import by.epam.finalproject.controller.command.impl.question.post.impl.DeleteQuestionStrategy;
import by.epam.finalproject.controller.command.impl.question.post.impl.EditQuestionStrategy;

import java.util.HashMap;
import java.util.Map;

import static by.epam.finalproject.controller.command.constant.HttpParameterName.CREATE_DO_PARAMETER_NAME;
import static by.epam.finalproject.controller.command.constant.HttpParameterName.DELETE_DO_PARAMETER_NAME;
import static by.epam.finalproject.controller.command.constant.HttpParameterName.EDIT_DO_PARAMETER_NAME;


public class QuestionDoPostStrategyResolver {

    private static final QuestionDoPostStrategyResolver QUESTION_STRATEGY_RESOLVER = new QuestionDoPostStrategyResolver();
    private final Map<String, QuestionDoPostStrategy> strategies = new HashMap<>();

    private QuestionDoPostStrategyResolver() {
        strategies.put(CREATE_DO_PARAMETER_NAME, new CreateQuestionStrategy());
        strategies.put(EDIT_DO_PARAMETER_NAME, new EditQuestionStrategy());
        strategies.put(DELETE_DO_PARAMETER_NAME, new DeleteQuestionStrategy());
    }

    public static QuestionDoPostStrategyResolver getInstance() {
        return QUESTION_STRATEGY_RESOLVER;
    }

    public QuestionDoPostStrategy getStrategy(String type) {
        return strategies.get(type);
    }

}
