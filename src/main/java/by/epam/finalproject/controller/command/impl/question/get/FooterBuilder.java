package by.epam.finalproject.controller.command.impl.question.get;

import by.epam.finalproject.controller.command.impl.question.get.impl.AllQuestionsStrategy;
import by.epam.finalproject.entity.Question;
import by.epam.finalproject.service.QuestionService;
import by.epam.finalproject.service.ServiceFactory;
import by.epam.finalproject.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static by.epam.finalproject.controller.command.constant.HttpParameterName.RANDOM_QUESTION_PARAMETER_NAME;


public class FooterBuilder {

    private static final FooterBuilder FOOTER_BUILDER = new FooterBuilder();

    private final static Logger logger = Logger.getLogger(AllQuestionsStrategy.class);

    private QuestionService questionService = ServiceFactory.getInstance().getQuestionService();

    private FooterBuilder() {
    }

    public static FooterBuilder getInstance() {
        return FOOTER_BUILDER;
    }

    public void setFooterContent(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<Question> randomQuestion = questionService.getRandomQuestion();
        request.setAttribute(RANDOM_QUESTION_PARAMETER_NAME, randomQuestion);
        logger.debug("Footer has been successfully created.");
    }

}
