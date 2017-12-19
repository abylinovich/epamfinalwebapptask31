package by.epam.final_project.controller.command.impl;

import by.epam.final_project.entity.User;
import by.epam.final_project.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.final_project.controller.command.message.FrontMessageUtil.CANNOT_LOGIN_MESSAGE;
import static by.epam.final_project.controller.command.message.FrontMessageUtil.USER_NOT_FOUND_MESSAGE;
import static by.epam.final_project.controller.command.message.HTTPParameterNameUtil.*;
import static by.epam.final_project.controller.command.message.PagePathUtil.ERROR_PAGE_PATH;
import static by.epam.final_project.controller.command.message.PagePathUtil.HOME_PAGE_PATH;
import static by.epam.final_project.controller.command.message.PagePathUtil.LOGIN_PAGE_PATH;

public class LoginCommand extends AbstractCommand {

    private final static Logger logger = Logger.getLogger(LoginCommand.class);

    @Override
    public void doGet(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(LOGIN_PAGE_PATH).forward(request, response);
    }

    @Override
    public void doPost(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(LOGIN_PARAMETER_NAME);
        String password = request.getParameter(PASSWORD_PARAMETER_NAME);
        try {
            User user = userService.findUserByLoginAndPassword(login, password);
            if(user != null) {
                request.setAttribute(USER_PARAMETER_NAME, user);
                request.getRequestDispatcher(HOME_PAGE_PATH).forward(request, response);
                logger.debug("Forward to home page.");
                return;
            } else {
                request.setAttribute(ERROR_MESSAGE_PARAMETER_NAME, USER_NOT_FOUND_MESSAGE);
            }
        } catch (ServiceException e) {
            logger.error("Cannot login user.", e);
            request.setAttribute(ERROR_MESSAGE_PARAMETER_NAME, CANNOT_LOGIN_MESSAGE);
        }
        logger.debug("Forward to error page.");
        request.getRequestDispatcher(ERROR_PAGE_PATH).forward(request, response);
    }

}
