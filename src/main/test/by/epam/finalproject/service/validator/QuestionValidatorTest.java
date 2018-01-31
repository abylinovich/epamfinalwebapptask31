package by.epam.finalproject.service.validator;


import by.epam.finalproject.entity.Question;

import by.epam.finalproject.entity.Theme;
import by.epam.finalproject.entity.User;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class QuestionValidatorTest {

    private QuestionValidator validator;
    Question question;


    @Before
    public void setUp() throws Exception {
        validator = ValidatorFactory.getInstance().getQuestionValidator();

    }

    @Test
    public void test_null() {
        question = null;
        assertFalse(validator.validate(question));
    }

    @Test
    public void test_empty() {
        question = new Question();
        assertFalse(validator.validate(question));
    }

    @Test
    public void test_questionIllegal() {
        question = new Question();
        question.setQuestion("!");
        assertFalse(validator.validate(question));
    }

    @Test
    public void test_questionNoTheme() {
        question = new Question();
        question.setTheme(new Theme());
        assertFalse(validator.validate(question));
    }

    @Test
    public void test_questionNoUser() {
        question = new Question();
        question.setUser(new User());
        assertFalse(validator.validate(question));
    }

}
