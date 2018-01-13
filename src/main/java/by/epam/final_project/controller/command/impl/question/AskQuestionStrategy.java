package by.epam.final_project.controller.command.impl.question;

import by.epam.final_project.entity.Theme;
import by.epam.final_project.entity.User;
import by.epam.final_project.service.ServiceFactory;
import by.epam.final_project.service.ThemeService;
import by.epam.final_project.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Locale;

import static by.epam.final_project.controller.command.constant.HttpParameterName.*;

public class AskQuestionStrategy implements QuestionStrategy {

    private final static Logger logger = Logger.getLogger(AskQuestionStrategy.class);

    private ThemeService themeService = ServiceFactory.getInstance().getThemeService();

    @Override
    public void setPageContent(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute(INPUT_PARAMETER_NAME, true);

            String language = (String) request.getSession().getAttribute(LANGUAGE_PARAMETER_NAME);
            if(language == null) {
                User user = (User) request.getSession().getAttribute(USER_PARAMETER_NAME);
                language = user.getLocale().getLanguage();
            }
            Locale locale = new Locale(language);

            List<Theme> themes = themeService.getThemes(locale);
            request.setAttribute(THEMES_PARAMETER_NAME, themes);
        } catch (ServiceException e) {
            logger.error("Cannot render ask question page.", e);
        }
    }

}
