package by.epam.final_project.controller.command.impl;

import by.epam.final_project.controller.command.Command;
import by.epam.final_project.entity.Question;
import by.epam.final_project.service.QuestionService;
import by.epam.final_project.service.ServiceFactory;
import by.epam.final_project.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.epam.final_project.controller.command.constant.FrontMessage.POST_METHOD_ERROR_MESSAGE;
import static by.epam.final_project.controller.command.constant.HttpParameterName.*;
import static by.epam.final_project.controller.command.constant.PagePath.ERROR_PAGE_PATH;
import static by.epam.final_project.controller.command.constant.PagePath.MAIN_PAGE_PATH;

public class UserCommand implements Command {

    private final static Logger logger = Logger.getLogger(UserCommand.class);

    private QuestionService questionService = ServiceFactory.getInstance().getQuestionService();


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String userId = request.getParameter(ID_PARAMETER_NAME);
            List<Question> userQuestions = questionService.getQuestionsByUser(userId);
            request.setAttribute(QUESTIONS_PARAMETER_NAME, userQuestions);
        } catch (ServiceException e) {
            request.setAttribute(QUESTIONS_PARAMETER_NAME, null);
        }

        try {
            List<Question> randomQuestion = questionService.getRandomQuestion();
            request.setAttribute(RANDOM_QUESTION_PARAMETER_NAME, randomQuestion);
        } catch (ServiceException e) {
            request.setAttribute(RANDOM_QUESTION_PARAMETER_NAME, null);
        }
        logger.debug("Show main page.");
        request.getRequestDispatcher(MAIN_PAGE_PATH).forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(ERROR_MESSAGE_PARAMETER_NAME, POST_METHOD_ERROR_MESSAGE);
        request.getRequestDispatcher(ERROR_PAGE_PATH).forward(request, response);
        logger.debug("Forward to error page. Method POST is not available.");
    }

}
