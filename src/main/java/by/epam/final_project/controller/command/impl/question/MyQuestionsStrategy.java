package by.epam.final_project.controller.command.impl.question;

import by.epam.final_project.entity.Question;
import by.epam.final_project.entity.User;
import by.epam.final_project.service.QuestionService;
import by.epam.final_project.service.ServiceFactory;
import by.epam.final_project.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static by.epam.final_project.controller.command.constant.HttpParameterName.QUESTIONS_PARAMETER_NAME;
import static by.epam.final_project.controller.command.constant.HttpParameterName.USER_PARAMETER_NAME;

public class MyQuestionsStrategy implements QuestionStrategy {

    private final static Logger logger = Logger.getLogger(MyQuestionsStrategy.class);

    private QuestionService questionService = ServiceFactory.getInstance().getQuestionService();


    @Override
    public void setPageContent(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = (User) request.getSession().getAttribute(USER_PARAMETER_NAME);
            List<Question> myQuestions = questionService.getQuestionsByUser(String.valueOf(user.getUserId()));
            request.setAttribute(QUESTIONS_PARAMETER_NAME, myQuestions);
        } catch (ServiceException e) {
            logger.error("Cannot reach questions.", e);
        }
    }

}
