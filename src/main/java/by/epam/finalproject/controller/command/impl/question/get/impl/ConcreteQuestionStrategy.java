package by.epam.finalproject.controller.command.impl.question.get.impl;

import by.epam.finalproject.controller.command.impl.question.get.QuestionDoGetStrategy;
import by.epam.finalproject.entity.Question;
import by.epam.finalproject.service.QuestionService;
import by.epam.finalproject.service.ServiceFactory;
import by.epam.finalproject.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.epam.finalproject.controller.command.constant.HttpParameterName.ID_PARAMETER_NAME;
import static by.epam.finalproject.controller.command.constant.HttpParameterName.QUESTION_PARAMETER_NAME;


public class ConcreteQuestionStrategy implements QuestionDoGetStrategy {

    private final static Logger logger = Logger.getLogger(ConcreteQuestionStrategy.class);

    private QuestionService questionService = ServiceFactory.getInstance().getQuestionService();


    @Override
    public void processGet(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String questionId = request.getParameter(ID_PARAMETER_NAME);
        Question question = questionService.getQuestion(questionId);
        request.setAttribute(QUESTION_PARAMETER_NAME, question);
        logger.debug("Question page has been successfully created.");
    }

}
