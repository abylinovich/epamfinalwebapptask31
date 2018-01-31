package by.epam.finalproject.service.impl;

import by.epam.finalproject.dao.DAOFactory;
import by.epam.finalproject.dao.ThemeDAO;
import by.epam.finalproject.dao.exception.DAOException;
import by.epam.finalproject.entity.Theme;
import by.epam.finalproject.service.ThemeService;
import by.epam.finalproject.service.exception.ServiceException;
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
