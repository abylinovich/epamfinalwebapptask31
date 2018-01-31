package by.epam.finalproject.controller.command.impl;

import by.epam.finalproject.controller.command.Command;
import by.epam.finalproject.entity.User;
import by.epam.finalproject.service.UserService;
import by.epam.finalproject.service.ServiceFactory;
import by.epam.finalproject.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.finalproject.controller.command.constant.HttpParameterName.PASSWORD_PARAMETER_NAME;
import static by.epam.finalproject.controller.command.constant.HttpParameterName.USERNAME_PARAMETER_NAME;
import static by.epam.finalproject.controller.command.constant.HttpParameterName.NO_SUCH_USER_ERROR_ATTRIBUTE_NAME;
import static by.epam.finalproject.controller.command.constant.HttpParameterName.USER_PARAMETER_NAME;
import static by.epam.finalproject.controller.command.constant.HttpParameterName.CANNOT_LOGIN_ERROR_ATTRIBUTE_NAME;
import static by.epam.finalproject.controller.command.constant.PagePath.LOGIN_PAGE_PATH;
import static by.epam.finalproject.controller.command.constant.PagePath.MY_QUESTIONS_URL_PATTERN;
import static by.epam.finalproject.controller.command.constant.PagePath.ERROR_PAGE_PATH;


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
                response.sendRedirect(MY_QUESTIONS_URL_PATTERN);
                return;
            } else {
                request.setAttribute(NO_SUCH_USER_ERROR_ATTRIBUTE_NAME, true);
            }
        } catch (ServiceException e) {
            logger.error("Cannot login user.", e);
            request.setAttribute(CANNOT_LOGIN_ERROR_ATTRIBUTE_NAME, true);
        }
        logger.debug("Login failed. Redirect to error page.");
        request.getRequestDispatcher(ERROR_PAGE_PATH).forward(request, response);
    }

}
