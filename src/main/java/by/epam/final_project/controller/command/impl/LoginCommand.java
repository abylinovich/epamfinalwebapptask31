package by.epam.final_project.controller.command.impl;

import by.epam.final_project.controller.command.Command;
import by.epam.final_project.entity.User;
import by.epam.final_project.service.UserService;
import by.epam.final_project.service.ServiceFactory;
import by.epam.final_project.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.final_project.controller.command.constant.FrontMessage.CANNOT_LOGIN_MESSAGE;
import static by.epam.final_project.controller.command.constant.FrontMessage.USER_NOT_FOUND_MESSAGE;
import static by.epam.final_project.controller.command.constant.HttpParameterName.*;
import static by.epam.final_project.controller.command.constant.PagePath.ERROR_PAGE_PATH;
import static by.epam.final_project.controller.command.constant.PagePath.HOME_PAGE_PATH;
import static by.epam.final_project.controller.command.constant.PagePath.LOGIN_PAGE_PATH;

public class LoginCommand implements Command {

    private final static Logger logger = Logger.getLogger(LoginCommand.class);

    private UserService userService = ServiceFactory.getInstance().getUserService();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(LOGIN_PAGE_PATH).forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(USERNAME_PARAMETER_NAME);
        String password = request.getParameter(PASSWORD_PARAMETER_NAME);
        User user = null;
        try {
            user = userService.findUser(login, password);
            if(user != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute(USER_PARAMETER_NAME, user);
                logger.debug("Login successful. Redirect to home page.");
                response.sendRedirect(HOME_PAGE_PATH);
                return;
            } else {
                request.setAttribute(ERROR_MESSAGE_PARAMETER_NAME, USER_NOT_FOUND_MESSAGE);
            }
        } catch (ServiceException e) {
            logger.error("Cannot login user.", e);
            request.setAttribute(ERROR_MESSAGE_PARAMETER_NAME, CANNOT_LOGIN_MESSAGE);
        }
        logger.debug("Login failed. Redirect to error page.");
        request.getRequestDispatcher(ERROR_PAGE_PATH).forward(request, response);
    }

}
