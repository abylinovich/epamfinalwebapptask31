package by.epam.final_project.controller.command.impl.question;

import by.epam.final_project.controller.command.Paginator;
import by.epam.final_project.controller.command.PaginatorFactory;
import by.epam.final_project.entity.Question;
import by.epam.final_project.service.QuestionService;
import by.epam.final_project.service.ServiceFactory;
import by.epam.final_project.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static by.epam.final_project.controller.command.constant.HttpParameterName.COUNT_PARAMETER_NAME;
import static by.epam.final_project.controller.command.constant.HttpParameterName.PAGE_PARAMETER_NAME;
import static by.epam.final_project.controller.command.constant.HttpParameterName.QUESTIONS_PARAMETER_NAME;
import static by.epam.final_project.controller.command.constant.HttpParameterName.TOTAL_QUESTIONS_ATTRIBUTE_NAME;


public class AllQuestionsStrategy implements QuestionStrategy {

    private final static Logger logger = Logger.getLogger(AllQuestionsStrategy.class);

    private QuestionService questionService = ServiceFactory.getInstance().getQuestionService();
    private Paginator paginator = PaginatorFactory.getInstance().getPaginator();


    @Override
    public void setPageContent(HttpServletRequest request, HttpServletResponse response) {
        String page = paginator.checkParameter(request, PAGE_PARAMETER_NAME);
        String count = paginator.checkParameter(request, COUNT_PARAMETER_NAME);

        try {
            int total = questionService.getQuestionsCount();
            request.getSession().setAttribute(TOTAL_QUESTIONS_ATTRIBUTE_NAME, String.valueOf(total));

            List<Question> questions = questionService.getQuestions(page, count);
            logger.debug("Get '" + count + "' questions for page '" + page + "'.");
            request.setAttribute(QUESTIONS_PARAMETER_NAME, questions);
        } catch (ServiceException e) {
            logger.error("Cannot get questions.", e);
        }
    }

}
