package by.epam.final_project.controller.command.impl.question;

import by.epam.final_project.controller.command.Command;
import by.epam.final_project.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

import static by.epam.final_project.controller.command.constant.HttpParameterName.*;

public class QuestionStrategyResolver {

    private static final QuestionStrategyResolver QUESTION_STRATEGY_RESOLVER = new QuestionStrategyResolver();
    private final Map<String, QuestionStrategy> strategies = new HashMap<>();

    private QuestionStrategyResolver() {
        strategies.put(GET_DO_PARAMETER_VALUE, new ConcreteQuestionStrategy());
        strategies.put(ALL_DO_PARAMETER_VALUE, new AllQuestionsStrategy());
        strategies.put(MY_DO_PARAMETER_VALUE, new MyQuestionsStrategy());
        strategies.put(ASK_DO_PARAMETER_VALUE, new AskQuestionStrategy());
    }

    public static QuestionStrategyResolver getInstance() {
        return QUESTION_STRATEGY_RESOLVER;
    }

    public QuestionStrategy getStrategy(String type) {
        return strategies.get(type);
    }

}
