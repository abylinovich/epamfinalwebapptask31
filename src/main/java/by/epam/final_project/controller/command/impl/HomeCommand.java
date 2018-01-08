package by.epam.final_project.controller.command.impl;

import by.epam.final_project.controller.command.Command;
import by.epam.final_project.entity.Question;
import by.epam.final_project.service.QuestionService;
import by.epam.final_project.service.UserService;
import by.epam.final_project.service.ServiceFactory;
import by.epam.final_project.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.final_project.controller.command.constant.FrontMessage.RANDOM_QUESTION_NOT_FOUND_MESSAGE;
import static by.epam.final_project.controller.command.constant.HttpParameterName.NO_RANDOM_QUESTION_PARAMETER_NAME;
import static by.epam.final_project.controller.command.constant.HttpParameterName.RANDOM_QUESTION_PARAMETER_NAME;
import static by.epam.final_project.controller.command.constant.PagePath.HOME_PAGE_PATH;

public class HomeCommand implements Command {

    private final static Logger logger = Logger.getLogger(HomeCommand.class);

    private UserService userService = ServiceFactory.getInstance().getUserService();
    private QuestionService questionService = ServiceFactory.getInstance().getQuestionService();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setRandomQuestion(request);
        request.getRequestDispatcher(HOME_PAGE_PATH).forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new ServletException("Method POST is not available for this command.");
    }

    private void setRandomQuestion(HttpServletRequest request) {
        try {
            Question question = questionService.getRandomQuestion();
            request.setAttribute(RANDOM_QUESTION_PARAMETER_NAME, question);
            logger.debug("Random question was set on page.");
        } catch (ServiceException e) {
            logger.error("Random question was not found.", e);
            request.setAttribute(NO_RANDOM_QUESTION_PARAMETER_NAME, RANDOM_QUESTION_NOT_FOUND_MESSAGE);
        }
    }

}
