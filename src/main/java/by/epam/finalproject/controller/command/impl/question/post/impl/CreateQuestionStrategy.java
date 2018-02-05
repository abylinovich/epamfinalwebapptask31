package by.epam.finalproject.controller.command.impl.question.post.impl;

import by.epam.finalproject.controller.command.impl.question.post.QuestionDoPostStrategy;
import by.epam.finalproject.entity.Question;
import by.epam.finalproject.entity.Theme;
import by.epam.finalproject.entity.User;
import by.epam.finalproject.service.QuestionService;
import by.epam.finalproject.service.ServiceFactory;
import by.epam.finalproject.service.UserService;
import by.epam.finalproject.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.epam.finalproject.controller.command.constant.HttpParameterName.QUESTION_DATA_PARAMETER_NAME;
import static by.epam.finalproject.controller.command.constant.HttpParameterName.QUESTION_THEME_PARAMETER_NAME;
import static by.epam.finalproject.controller.command.constant.HttpParameterName.QUESTION_TITLE_PARAMETER_NAME;
import static by.epam.finalproject.controller.command.constant.HttpParameterName.USER_PARAMETER_NAME;


public class CreateQuestionStrategy implements QuestionDoPostStrategy {

    private final static Logger logger = Logger.getLogger(CreateQuestionStrategy.class);

    private QuestionService questionService = ServiceFactory.getInstance().getQuestionService();


    @Override
    public void processPost(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
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

        questionService.addQuestion(question);
        logger.debug("Create command has been successfully completed.");
    }

}
