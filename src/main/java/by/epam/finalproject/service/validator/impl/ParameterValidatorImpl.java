package by.epam.finalproject.service.validator.impl;

import by.epam.finalproject.service.validator.ParameterValidator;
import org.apache.log4j.Logger;

import java.util.regex.Pattern;

public class ParameterValidatorImpl implements ParameterValidator {

    private final static Logger logger = Logger.getLogger(ParameterValidatorImpl.class);

    private static final String NUMERIC_REGEX = "\\d+";

    private Pattern numericPattern = Pattern.compile(NUMERIC_REGEX);

    @Override
    public boolean validateNumeric(String value) {
        if(value == null) {
            logger.debug("Validation error. Value is null.");
            return false;
        }
        return numericPattern.matcher(value).matches();
    }

}
