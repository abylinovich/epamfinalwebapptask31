package by.epam.final_project.service.validator.impl;

import by.epam.final_project.entity.Theme;
import by.epam.final_project.service.validator.ThemeValidator;
import org.apache.log4j.Logger;

public class ThemeValidatorImpl implements ThemeValidator {

    private final static Logger logger = Logger.getLogger(ThemeValidatorImpl.class);


    @Override
    public boolean validate(Theme theme) {
        Integer id = theme.getThemeId();
        if(id == null || id <= 0) {
            logger.debug("Validation error. Invalid theme id='" + id + "'.");
            return false;
        }
        return true;
    }

}
