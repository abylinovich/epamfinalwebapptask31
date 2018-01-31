package by.epam.finalproject.controller.command.impl.question;

import by.epam.finalproject.entity.Question;
import by.epam.finalproject.service.QuestionService;
import by.epam.finalproject.service.ServiceFactory;
import by.epam.finalproject.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.epam.finalproject.controller.command.constant.HttpParameterName.ID_PARAMETER_NAME;
import static by.epam.finalproject.controller.command.constant.HttpParameterName.QUESTION_PARAMETER_NAME;

public class ConcreteQuestionStrategy implements QuestionStrategy {

    private final static Logger logger = Logger.getLogger(ConcreteQuestionStrategy.class);

    private QuestionService questionService = ServiceFactory.getInstance().getQuestionService();


    @Override
    public void setPageContent(HttpServletRequest request, HttpServletResponse response) {
        String questionId = request.getParameter(ID_PARAMETER_NAME);
        try {
            Question question = questionService.getQuestion(questionId);
            request.setAttribute(QUESTION_PARAMETER_NAME, question);

        } catch (ServiceException e) {
            logger.error("Cannot reach question id='" + questionId + "'.", e);
        }
    }

}
