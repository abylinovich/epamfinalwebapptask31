package by.epam.final_project.service.impl;

import by.epam.final_project.dao.DAOFactory;
import by.epam.final_project.dao.ThemeDAO;
import by.epam.final_project.dao.exception.DAOException;
import by.epam.final_project.entity.Theme;
import by.epam.final_project.service.ThemeService;
import by.epam.final_project.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Locale;

public class ThemeServiceImpl implements ThemeService {

    private static final Logger logger = Logger.getLogger(ThemeServiceImpl.class);

    private ThemeDAO themeDAO = DAOFactory.getInstance().getThemeDAO();


    @Override
    public List<Theme> getThemes(Locale locale) throws ServiceException {
        try {
            return themeDAO.findThemes(locale);
        } catch (DAOException e) {
            throw new ServiceException("Cannot get all themes.", e);
        }
    }

}
