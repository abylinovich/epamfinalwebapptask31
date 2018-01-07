package by.epam.final_project.controller.command.impl;

import by.epam.final_project.controller.command.Command;
import by.epam.final_project.entity.User;
import by.epam.final_project.entity.UserRole;
import by.epam.final_project.service.UserService;
import by.epam.final_project.service.UserServiceFactory;
import by.epam.final_project.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

import static by.epam.final_project.controller.command.constant.FrontMessage.CANNOT_REGISTER_USER_MESSAGE;
import static by.epam.final_project.controller.command.constant.HttpParameterName.*;
import static by.epam.final_project.controller.command.constant.PagePath.ERROR_PAGE_PATH;
import static by.epam.final_project.controller.command.constant.PagePath.HOME_PAGE_PATH;
import static by.epam.final_project.controller.command.constant.PagePath.REGISTER_PAGE_PATH;

public class RegisterCommand implements Command {

    private final static Logger logger = Logger.getLogger(RegisterCommand.class);

    private UserService userService = UserServiceFactory.getInstance().getUserService();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(REGISTER_PAGE_PATH).forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = setUserDetails(request);
        try {
            userService.register(user);
            userService.resetPassword(user);
            request.setAttribute(USERNAME_PARAMETER_NAME, user);
            logger.debug("Registration successful. Redirect to home page.");

            HttpSession session = request.getSession(true);
            session.setAttribute(USERNAME_PARAMETER_NAME, user);
            response.sendRedirect(HOME_PAGE_PATH);
        } catch (ServiceException e) {
            logger.error("Cannot register user.", e);
            request.setAttribute(ERROR_MESSAGE_PARAMETER_NAME, CANNOT_REGISTER_USER_MESSAGE);
            request.getRequestDispatcher(ERROR_PAGE_PATH).forward(request, response);
        }
    }

    private User setUserDetails(HttpServletRequest request) {
        User user = new User();
        user.setRole(UserRole.USER);
        user.setUsername(request.getParameter(USERNAME_PARAMETER_NAME));
        user.setPassword(request.getParameter(PASSWORD_PARAMETER_NAME));
        user.setFirstName(request.getParameter(FIRST_NAME_PARAMETER_NAME));
        user.setLastName(request.getParameter(LAST_NAME_PARAMETER_NAME));
        user.setEmail(request.getParameter(EMAIL_PARAMETER_NAME));
        user.setAge(Integer.valueOf(request.getParameter(AGE_PARAMETER_NAME)));
        user.setLocale(
                new Locale(
                        request.getParameter(LOCALE_PARAMETER_NAME)
                )
        );
        return user;
    }

}
