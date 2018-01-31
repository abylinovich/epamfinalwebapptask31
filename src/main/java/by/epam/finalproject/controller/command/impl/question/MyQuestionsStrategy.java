package by.epam.finalproject.controller.command.impl.question;

import by.epam.finalproject.controller.paginator.Paginator;
import by.epam.finalproject.controller.paginator.PaginatorFactory;
import by.epam.finalproject.entity.Question;
import by.epam.finalproject.entity.User;
import by.epam.finalproject.service.QuestionService;
import by.epam.finalproject.service.ServiceFactory;
import by.epam.finalproject.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static by.epam.finalproject.controller.command.constant.HttpParameterName.COUNT_PARAMETER_NAME;
import static by.epam.finalproject.controller.command.constant.HttpParameterName.PAGE_PARAMETER_NAME;
import static by.epam.finalproject.controller.command.constant.HttpParameterName.USER_PARAMETER_NAME;
import static by.epam.finalproject.controller.command.constant.HttpParameterName.QUESTIONS_PARAMETER_NAME;
import static by.epam.finalproject.controller.command.constant.HttpParameterName.TOTAL_QUESTIONS_ATTRIBUTE_NAME;


public class MyQuestionsStrategy implements QuestionStrategy {

    private final static Logger logger = Logger.getLogger(MyQuestionsStrategy.class);

    private QuestionService questionService = ServiceFactory.getInstance().getQuestionService();
    private Paginator paginator = PaginatorFactory.getInstance().getPaginator();


    @Override
    public void setPageContent(HttpServletRequest request, HttpServletResponse response) {
        String page = paginator.checkParameter(request, PAGE_PARAMETER_NAME);
        String count = paginator.checkParameter(request, COUNT_PARAMETER_NAME);

        try {
            User user = (User) request.getSession().getAttribute(USER_PARAMETER_NAME);

            int total = questionService.getQuestionsCountForUser(String.valueOf(user.getUserId()));
            request.getSession().setAttribute(TOTAL_QUESTIONS_ATTRIBUTE_NAME, String.valueOf(total));

            List<Question> questions = questionService.getQuestionsByUser(String.valueOf(user.getUserId()), page, count);
            logger.debug("Get '" + count + "' questions for user id=" + user.getUserId() + " page '" + page + "'.");
            request.setAttribute(QUESTIONS_PARAMETER_NAME, questions);
        } catch (ServiceException e) {
            logger.error("Cannot reach questions.", e);
        }
    }

}