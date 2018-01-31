package by.epam.final_project.controller.command.impl;

import by.epam.final_project.controller.command.Command;
import by.epam.final_project.controller.command.impl.question.FooterBuilder;
import by.epam.final_project.controller.command.impl.question.QuestionStrategy;
import by.epam.final_project.controller.command.impl.question.QuestionStrategyResolver;
import by.epam.final_project.entity.Question;
import by.epam.final_project.entity.Theme;
import by.epam.final_project.entity.User;
import by.epam.final_project.service.QuestionService;
import by.epam.final_project.service.ServiceFactory;
import by.epam.final_project.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.final_project.controller.command.constant.HttpParameterName.DO_PARAMETER_NAME;
import static by.epam.final_project.controller.command.constant.HttpParameterName.QUESTION_TITLE_PARAMETER_NAME;
import static by.epam.final_project.controller.command.constant.HttpParameterName.USER_PARAMETER_NAME;
import static by.epam.final_project.controller.command.constant.HttpParameterName.ADD_QUESTION_ERROR_ATTRIBUTE_NAME;
import static by.epam.final_project.controller.command.constant.HttpParameterName.QUESTION_DATA_PARAMETER_NAME;
import static by.epam.final_project.controller.command.constant.HttpParameterName.QUESTION_THEME_PARAMETER_NAME;
import static by.epam.final_project.controller.command.constant.PagePath.ERROR_PAGE_PATH;
import static by.epam.final_project.controller.command.constant.PagePath.MAIN_PAGE_PATH;
import static by.epam.final_project.controller.command.constant.PagePath.MY_QUESTIONS_URL_PATTERN;

public class QuestionCommand implements Command {

    private final static Logger logger = Logger.getLogger(QuestionCommand.class);

    private QuestionStrategyResolver strategyResolver = QuestionStrategyResolver.getInstance();
    private FooterBuilder footerBuilder = FooterBuilder.getInstance();
    private QuestionService questionService = ServiceFactory.getInstance().getQuestionService();


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter(DO_PARAMETER_NAME);
        QuestionStrategy questionStrategy = strategyResolver.getStrategy(type);
        String forwardPagePath;
        if(questionStrategy != null) {
            questionStrategy.setPageContent(request, response);
            footerBuilder.setFooterContent(request, response);
            forwardPagePath = MAIN_PAGE_PATH;
            logger.debug("Show main page.");
        } else {
            forwardPagePath = ERROR_PAGE_PATH;
            logger.debug("Invalid request. Show error page.");
        }
        request.getRequestDispatcher(forwardPagePath).forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Question question = new Question();

        String title = request.getParameter(QUESTION_TITLE_PARAMETER_NAME);
        question.setTitle(title);

        String data = request.getParameter(QUESTION_DATA_PARAMETER_NAME);
        question.setQuestion(data);

        User user = (User) request.getSession().getAttribute(USER_PARAMETER_NAME);
        question.setUser(user);

        Theme theme = new Theme();
        Integer themeId;
        try {
            themeId = Integer.valueOf(request.getParameter(QUESTION_THEME_PARAMETER_NAME));
        } catch (NumberFormatException e) {
            themeId = null;
        }
        theme.setThemeId(themeId);
        question.setTheme(theme);

        try {
            questionService.addQuestion(question);
            response.sendRedirect(MY_QUESTIONS_URL_PATTERN);
            logger.debug("New question has been created successfully. Forward to my questions page.");
        } catch (ServiceException e) {
            logger.error("Cannot add question.", e);
            request.setAttribute(ADD_QUESTION_ERROR_ATTRIBUTE_NAME, true);
            request.getRequestDispatcher(ERROR_PAGE_PATH).forward(request, response);
            logger.debug("Creating question failed. Redirect to error page.");
        }
    }

}
