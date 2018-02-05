package by.epam.finalproject.controller.command.impl;

import by.epam.finalproject.controller.command.Command;
import by.epam.finalproject.controller.command.impl.question.get.FooterBuilder;
import by.epam.finalproject.controller.command.impl.question.get.QuestionDoGetStrategy;
import by.epam.finalproject.controller.command.impl.question.get.QuestionDoGetFactory;
import by.epam.finalproject.controller.command.impl.question.post.QuestionDoPostStrategyResolver;
import by.epam.finalproject.controller.command.impl.question.post.QuestionDoPostStrategy;
import by.epam.finalproject.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.finalproject.controller.command.constant.HttpParameterName.BAD_REQUEST_PARAMETER_ERROR_ATTRIBUTE_NAME;
import static by.epam.finalproject.controller.command.constant.HttpParameterName.DO_PARAMETER_NAME;
import static by.epam.finalproject.controller.command.constant.HttpParameterName.SERVER_ERROR_ATTRIBUTE_NAME;
import static by.epam.finalproject.controller.command.constant.PagePath.ERROR_PAGE_PATH;
import static by.epam.finalproject.controller.command.constant.PagePath.MAIN_PAGE_PATH;
import static by.epam.finalproject.controller.command.constant.PagePath.MY_QUESTIONS_URL_PATTERN;

public class QuestionCommand implements Command {

    private final static Logger logger = Logger.getLogger(QuestionCommand.class);

    private QuestionDoGetFactory doGetFactory = QuestionDoGetFactory.getInstance();
    private QuestionDoPostStrategyResolver doPostFactory = QuestionDoPostStrategyResolver.getInstance();
    private FooterBuilder footerBuilder = FooterBuilder.getInstance();


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String type = request.getParameter(DO_PARAMETER_NAME);
            QuestionDoGetStrategy questionStrategy = doGetFactory.getStrategy(type);
            if(questionStrategy != null) {
                questionStrategy.processGet(request, response);
                footerBuilder.setFooterContent(request, response);
                request.getRequestDispatcher(MAIN_PAGE_PATH).forward(request, response);
                logger.debug("Show main page.");
                return;
            } else {
                logger.debug("No question strategy found.");
                request.setAttribute(BAD_REQUEST_PARAMETER_ERROR_ATTRIBUTE_NAME, true);
            }
        } catch (ServiceException e) {
            request.setAttribute(SERVER_ERROR_ATTRIBUTE_NAME, true);
            logger.error("Cannot create page.", e);
        }
        request.getRequestDispatcher(ERROR_PAGE_PATH).forward(request, response);
        logger.debug("Show error page.");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String type = request.getParameter(DO_PARAMETER_NAME);
            QuestionDoPostStrategy questionStrategy = doPostFactory.getStrategy(type);
            if(questionStrategy != null) {
                questionStrategy.processPost(request, response);
                response.sendRedirect(MY_QUESTIONS_URL_PATTERN);
                logger.debug("Show home page.");
                return;
            } else {
                logger.debug("No question strategy found.");
                request.setAttribute(BAD_REQUEST_PARAMETER_ERROR_ATTRIBUTE_NAME, true);
            }

        } catch (ServiceException e) {
            request.setAttribute(SERVER_ERROR_ATTRIBUTE_NAME, true);
            logger.error("Cannot process POST method.", e);
        }
        request.getRequestDispatcher(ERROR_PAGE_PATH).forward(request, response);
        logger.debug("Show error page.");
    }

}
