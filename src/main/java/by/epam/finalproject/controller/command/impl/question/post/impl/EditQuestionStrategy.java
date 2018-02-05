package by.epam.finalproject.controller.command.impl.question.post.impl;

import by.epam.finalproject.controller.command.impl.question.post.QuestionDoPostStrategy;
import by.epam.finalproject.entity.Question;
import by.epam.finalproject.service.QuestionService;
import by.epam.finalproject.service.ServiceFactory;
import by.epam.finalproject.service.UserService;
import by.epam.finalproject.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.epam.finalproject.controller.command.constant.HttpParameterName.ID_PARAMETER_NAME;
import static by.epam.finalproject.controller.command.constant.HttpParameterName.QUESTION_DATA_PARAMETER_NAME;
import static by.epam.finalproject.controller.command.constant.HttpParameterName.QUESTION_TITLE_PARAMETER_NAME;


public class EditQuestionStrategy implements QuestionDoPostStrategy {

    private final static Logger logger = Logger.getLogger(EditQuestionStrategy.class);

    private QuestionService questionService = ServiceFactory.getInstance().getQuestionService();


    @Override
    public void processPost(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Question question = new Question();

        String id = request.getParameter(ID_PARAMETER_NAME);
        try {
            question.setQuestionId(Integer.valueOf(id));
        } catch (NumberFormatException e) {
            throw new ServiceException("Wrong request. Illegal value id='" + id + "'.", e);
        }

        String title = request.getParameter(QUESTION_TITLE_PARAMETER_NAME);
        question.setTitle(title);

        String data = request.getParameter(QUESTION_DATA_PARAMETER_NAME);
        question.setQuestion(data);

        questionService.editQuestion(question);
        logger.debug("Edit command has been successfully completed.");
    }

}
