package by.epam.final_project.service;

import by.epam.final_project.entity.Theme;
import by.epam.final_project.service.exception.ServiceException;

import java.util.List;
import java.util.Locale;

public interface ThemeService {

    List<Theme> getThemes(Locale locale) throws ServiceException;

}
