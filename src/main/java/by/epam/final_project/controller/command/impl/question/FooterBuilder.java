package by.epam.final_project.controller.command.impl.question;

import by.epam.final_project.entity.Question;
import by.epam.final_project.service.QuestionService;
import by.epam.final_project.service.ServiceFactory;
import by.epam.final_project.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static by.epam.final_project.controller.command.constant.HttpParameterName.RANDOM_QUESTION_PARAMETER_NAME;

public class FooterBuilder {

    private static final FooterBuilder FOOTER_BUILDER = new FooterBuilder();

    private final static Logger logger = Logger.getLogger(AllQuestionsStrategy.class);

    private QuestionService questionService = ServiceFactory.getInstance().getQuestionService();

    private FooterBuilder() {
    }

    public static FooterBuilder getInstance() {
        return FOOTER_BUILDER;
    }

    public void setFooterContent(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Question> randomQuestion = questionService.getRandomQuestion();
            request.setAttribute(RANDOM_QUESTION_PARAMETER_NAME, randomQuestion);
        } catch (ServiceException e) {
            logger.error("Cannot reach random question.", e);
        }
    }

}
