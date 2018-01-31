package by.epam.finalproject.service.validator.impl;

import by.epam.finalproject.entity.Theme;
import by.epam.finalproject.service.validator.ThemeValidator;
import org.apache.log4j.Logger;

public class ThemeValidatorImpl implements ThemeValidator {

    private final static Logger logger = Logger.getLogger(ThemeValidatorImpl.class);


    @Override
    public boolean validate(Theme theme) {
        if(theme == null) {
            return false;
        }
        Integer id = theme.getThemeId();
        if(id == null || id <= 0) {
            logger.debug("Validation error. Invalid theme id='" + id + "'.");
            return false;
        }
        return true;
    }

}
