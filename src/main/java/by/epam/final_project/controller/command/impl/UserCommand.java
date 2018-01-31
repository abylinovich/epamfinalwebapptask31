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

import static by.epam.final_project.controller.command.constant.HttpParameterName.COUNT_PARAMETER_NAME;
import static by.epam.final_project.controller.command.constant.HttpParameterName.ID_PARAMETER_NAME;
import static by.epam.final_project.controller.command.constant.HttpParameterName.PAGE_PARAMETER_NAME;
import static by.epam.final_project.controller.command.constant.HttpParameterName.MESSAGE_ERROR_ATTRIBUTE_NAME;
import static by.epam.final_project.controller.command.constant.HttpParameterName.QUESTIONS_PARAMETER_NAME;
import static by.epam.final_project.controller.command.constant.HttpParameterName.RANDOM_QUESTION_PARAMETER_NAME;
import static by.epam.final_project.controller.command.constant.PagePath.ERROR_PAGE_PATH;
import static by.epam.final_project.controller.command.constant.PagePath.MAIN_PAGE_PATH;

public class UserCommand implements Command {

    private final static Logger logger = Logger.getLogger(UserCommand.class);

    private QuestionService questionService = ServiceFactory.getInstance().getQuestionService();


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = (String) request.getSession().getAttribute(PAGE_PARAMETER_NAME);
        String count = (String) request.getSession().getAttribute(COUNT_PARAMETER_NAME);

        try {
            String userId = request.getParameter(ID_PARAMETER_NAME);
            List<Question> userQuestions = questionService.getQuestionsByUser(userId, page, count);
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
        request.setAttribute(MESSAGE_ERROR_ATTRIBUTE_NAME, true);
        request.getRequestDispatcher(ERROR_PAGE_PATH).forward(request, response);
        logger.debug("Forward to error page. Method POST is not available.");
    }

}
