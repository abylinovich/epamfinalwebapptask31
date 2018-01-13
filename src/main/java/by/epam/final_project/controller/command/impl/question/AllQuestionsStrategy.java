package by.epam.final_project.controller.command.impl.question;

import by.epam.final_project.entity.Question;
import by.epam.final_project.service.QuestionService;
import by.epam.final_project.service.ServiceFactory;
import by.epam.final_project.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static by.epam.final_project.controller.command.constant.HttpParameterName.QUESTIONS_PARAMETER_NAME;
import static by.epam.final_project.controller.command.constant.HttpParameterName.TITLE_MESSAGE_PARAMETER_NAME;

public class AllQuestionsStrategy implements QuestionStrategy {

    private final static Logger logger = Logger.getLogger(AllQuestionsStrategy.class);

    private QuestionService questionService = ServiceFactory.getInstance().getQuestionService();

    @Override
    public void setPageContent(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Question> userQuestions = questionService.getQuestions();
            request.setAttribute(QUESTIONS_PARAMETER_NAME, userQuestions);
        } catch (ServiceException e) {
            logger.error("Cannot reach questions.", e);
        }
    }

}
