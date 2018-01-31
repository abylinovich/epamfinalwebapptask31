package by.epam.finalproject.service.validator;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ParameterValidatorTest {

    private ParameterValidator validator;


    @Before
    public void setUp() throws Exception {
        validator = ValidatorFactory.getInstance().getParameterValidator();

    }

    @Test
    public void test_null() {
        String value = null;
        assertFalse(validator.validateNumeric(value));
    }

    @Test
    public void test_empty() {
        String value = "";
        assertFalse(validator.validateNumeric(value));
    }

    @Test
    public void test_illegal() {
        String value = "!,2";
        assertFalse(validator.validateNumeric(value));
    }

    @Test
    public void test_legal() {
        String value = "42";
        assertTrue(validator.validateNumeric(value));
    }


}
