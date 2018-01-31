package by.epam.final_project.controller.command.impl;

import by.epam.final_project.controller.command.Command;
import by.epam.final_project.entity.Answer;
import by.epam.final_project.entity.Question;
import by.epam.final_project.entity.User;
import by.epam.final_project.service.AnswerService;
import by.epam.final_project.service.ServiceFactory;
import by.epam.final_project.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.final_project.controller.command.constant.HttpParameterName.ANSWER_PARAMETER_NAME;
import static by.epam.final_project.controller.command.constant.HttpParameterName.METHOD_ERROR_ATTRIBUTE_NAME;
import static by.epam.final_project.controller.command.constant.HttpParameterName.QUESTION_ID_PARAMETER_NAME;
import static by.epam.final_project.controller.command.constant.HttpParameterName.ADD_ANSWER_ERROR_ATTRIBUTE_NAME;
import static by.epam.final_project.controller.command.constant.HttpParameterName.USER_PARAMETER_NAME;
import static by.epam.final_project.controller.command.constant.HttpParameterName.REFERER_HEADER_NAME;
import static by.epam.final_project.controller.command.constant.PagePath.ERROR_PAGE_PATH;


public class AnswerCommand implements Command {

    private final static Logger logger = Logger.getLogger(AnswerCommand.class);

    private AnswerService answerService = ServiceFactory.getInstance().getAnswerService();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(METHOD_ERROR_ATTRIBUTE_NAME, true);
        request.getRequestDispatcher(ERROR_PAGE_PATH).forward(request, response);

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Answer answer = new Answer();

        String answerData = request.getParameter(ANSWER_PARAMETER_NAME);
        answer.setAnswer(answerData);

        Question question = new Question();
        Integer questionId;
        try {
            questionId = Integer.valueOf(request.getParameter(QUESTION_ID_PARAMETER_NAME));
        } catch (NumberFormatException e) {
            questionId = null;
        }
        question.setQuestionId(questionId);
        answer.setQuestion(question);

        User user = (User) request.getSession().getAttribute(USER_PARAMETER_NAME);
        answer.setUser(user);

        try {
            answerService.addAnswer(answer);
            String referer = request.getHeader(REFERER_HEADER_NAME);
            response.sendRedirect(referer);
            logger.debug("Answer has been successfully added. Refresh page.");
        } catch (ServiceException e) {
            logger.error("Cannot add answer.", e);
            request.setAttribute(ADD_ANSWER_ERROR_ATTRIBUTE_NAME, true);
            request.getRequestDispatcher(ERROR_PAGE_PATH).forward(request, response);
            logger.debug("Creating answer failed. Redirect to error page.");
        }
    }

}
