package by.epam.finalproject.controller.command.impl.question.post.impl;

import by.epam.finalproject.controller.command.impl.question.post.QuestionDoPostStrategy;
import by.epam.finalproject.entity.User;
import by.epam.finalproject.entity.UserRole;
import by.epam.finalproject.service.QuestionService;
import by.epam.finalproject.service.ServiceFactory;

import by.epam.finalproject.service.UserService;
import by.epam.finalproject.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static by.epam.finalproject.controller.command.constant.HttpParameterName.DELETE_QUESTION_ERROR_ATTRIBUTE_NAME;
import static by.epam.finalproject.controller.command.constant.HttpParameterName.ID_PARAMETER_NAME;
import static by.epam.finalproject.controller.command.constant.HttpParameterName.USER_PARAMETER_NAME;


public class DeleteQuestionStrategy implements QuestionDoPostStrategy {

    private final static Logger logger = Logger.getLogger(DeleteQuestionStrategy.class);

    private QuestionService questionService = ServiceFactory.getInstance().getQuestionService();
    private UserService userService = ServiceFactory.getInstance().getUserService();


    @Override
    public void processPost(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String questionId = request.getParameter(ID_PARAMETER_NAME);
        User user = (User) request.getSession().getAttribute(USER_PARAMETER_NAME);

        if(user.getRole() != UserRole.ADMIN) {
            User targetUser = userService.getUserByQuestionId(questionId);
            if(!user.getUserId().equals(targetUser.getUserId())) {
                request.setAttribute(DELETE_QUESTION_ERROR_ATTRIBUTE_NAME, true);
                throw new ServiceException("User name='" + user.getUsername() + "' has no right to delete question id='" + questionId + "'.");
            }
        }
        questionService.deleteQuestion(questionId);
        logger.debug("Delete command has been successfully completed.");
    }

}
