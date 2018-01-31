package by.epam.finalproject.service;

import by.epam.finalproject.entity.Theme;
import by.epam.finalproject.service.exception.ServiceException;

import java.util.List;
import java.util.Locale;

public interface ThemeService {

    List<Theme> getThemes(Locale locale) throws ServiceException;

}
