package by.epam.finalproject.controller.command.impl.question.get;

import by.epam.finalproject.controller.command.impl.question.get.impl.AllQuestionsStrategy;
import by.epam.finalproject.controller.command.impl.question.get.impl.AskQuestionStrategy;
import by.epam.finalproject.controller.command.impl.question.get.impl.ConcreteQuestionStrategy;
import by.epam.finalproject.controller.command.impl.question.get.impl.MyQuestionsStrategy;

import java.util.HashMap;
import java.util.Map;

import static by.epam.finalproject.controller.command.constant.HttpParameterName.ALL_DO_PARAMETER_NAME;
import static by.epam.finalproject.controller.command.constant.HttpParameterName.GET_DO_PARAMETER_NAME;
import static by.epam.finalproject.controller.command.constant.HttpParameterName.MY_DO_PARAMETER_NAME;
import static by.epam.finalproject.controller.command.constant.HttpParameterName.ASK_DO_PARAMETER_NAME;


public class QuestionDoGetFactory {

    private static final QuestionDoGetFactory QUESTION_STRATEGY_RESOLVER = new QuestionDoGetFactory();
    private final Map<String, QuestionDoGetStrategy> strategies = new HashMap<>();

    private QuestionDoGetFactory() {
        strategies.put(GET_DO_PARAMETER_NAME, new ConcreteQuestionStrategy());
        strategies.put(ALL_DO_PARAMETER_NAME, new AllQuestionsStrategy());
        strategies.put(MY_DO_PARAMETER_NAME, new MyQuestionsStrategy());
        strategies.put(ASK_DO_PARAMETER_NAME, new AskQuestionStrategy());
    }

    public static QuestionDoGetFactory getInstance() {
        return QUESTION_STRATEGY_RESOLVER;
    }

    public QuestionDoGetStrategy getStrategy(String type) {
        return strategies.get(type);
    }

}
