package by.epam.finalproject.dao;

import by.epam.finalproject.dao.exception.DAOException;
import by.epam.finalproject.entity.Theme;

import java.util.List;
import java.util.Locale;

public interface ThemeDAO {

    List<Theme> findThemes(Locale locale) throws DAOException;

}
