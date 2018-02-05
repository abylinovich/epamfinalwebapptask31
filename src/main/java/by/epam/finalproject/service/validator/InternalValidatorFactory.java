package by.epam.finalproject.service.validator;

import by.epam.finalproject.service.validator.impl.ThemeValidatorImpl;


public class InternalValidatorFactory {

    private static final InternalValidatorFactory INTERNAL_VALIDATOR_FACTORY = new InternalValidatorFactory();

    private final ThemeValidator themeValidator = new ThemeValidatorImpl();


    private InternalValidatorFactory() {
    }

    public static InternalValidatorFactory getInstance() {
        return INTERNAL_VALIDATOR_FACTORY;
    }

    public ThemeValidator getThemeValidator() {
        return themeValidator;
    }

}
