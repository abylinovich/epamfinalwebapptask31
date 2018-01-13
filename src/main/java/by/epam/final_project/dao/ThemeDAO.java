package by.epam.final_project.dao;

import by.epam.final_project.dao.exception.DAOException;
import by.epam.final_project.entity.Theme;

import java.util.List;
import java.util.Locale;

public interface ThemeDAO {

    List<Theme> findThemes(Locale locale) throws DAOException;

}
