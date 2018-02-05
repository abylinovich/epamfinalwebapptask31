package by.epam.finalproject.controller.command.impl.question.get.impl;

import by.epam.finalproject.controller.command.impl.question.get.QuestionDoGetStrategy;
import by.epam.finalproject.entity.Theme;
import by.epam.finalproject.entity.User;
import by.epam.finalproject.service.ServiceFactory;
import by.epam.finalproject.service.ThemeService;
import by.epam.finalproject.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Locale;

import static by.epam.finalproject.controller.command.constant.HttpParameterName.INPUT_PARAMETER_NAME;
import static by.epam.finalproject.controller.command.constant.HttpParameterName.LANGUAGE_PARAMETER_NAME;
import static by.epam.finalproject.controller.command.constant.HttpParameterName.USER_PARAMETER_NAME;
import static by.epam.finalproject.controller.command.constant.HttpParameterName.THEMES_PARAMETER_NAME;


public class AskQuestionStrategy implements QuestionDoGetStrategy {

    private final static Logger logger = Logger.getLogger(AskQuestionStrategy.class);

    private ThemeService themeService = ServiceFactory.getInstance().getThemeService();

    @Override
    public void processGet(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        request.setAttribute(INPUT_PARAMETER_NAME, true);

        String language = (String) request.getSession().getAttribute(LANGUAGE_PARAMETER_NAME);
        if(language == null) {
            User user = (User) request.getSession().getAttribute(USER_PARAMETER_NAME);
            language = user.getLocale().getLanguage();
        }
        Locale locale = new Locale(language);

        List<Theme> themes = themeService.getThemes(locale);
        request.setAttribute(THEMES_PARAMETER_NAME, themes);
        logger.debug("Ask question page has been successfully created.");
    }

}
