package by.epam.final_project.controller.command.impl;

import by.epam.final_project.controller.command.Command;
import by.epam.final_project.entity.User;
import by.epam.final_project.exception.ServiceException;
import by.epam.final_project.service.UserServiceFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.final_project.controller.command.message.FrontMessageUtil.CANNOT_LOGIN_MESSAGE;
import static by.epam.final_project.controller.command.message.HTTPParameterNameUtil.ERROR_MESSAGE_PARAMETER_NAME;
import static by.epam.final_project.controller.command.message.HTTPParameterNameUtil.LOGIN_PARAMETER_NAME;
import static by.epam.final_project.controller.command.message.HTTPParameterNameUtil.PASSWORD_PARAMETER_NAME;
import static by.epam.final_project.controller.command.message.HTTPParameterNameUtil.USER_PARAMETER_NAME;
import static by.epam.final_project.controller.command.message.PagePathUtil.ERROR_PAGE_PATH;
import static by.epam.final_project.controller.command.message.PagePathUtil.HOME_PAGE_PATH;

public class LoginCommand implements Command {

    private UserServiceFactory userServiceFactory = UserServiceFactory.getInstance();

    @Override
    public void process(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        String login = request.getParameter(LOGIN_PARAMETER_NAME);
        String password = request.getParameter(PASSWORD_PARAMETER_NAME);
        try {
            User user = userServiceFactory.getUserService().findUserByLoginAndPassword(login, password);
            request.setAttribute(USER_PARAMETER_NAME, user);
            request.getRequestDispatcher(HOME_PAGE_PATH).forward(request, response);
        } catch (ServiceException e) {
            request.setAttribute(ERROR_MESSAGE_PARAMETER_NAME, CANNOT_LOGIN_MESSAGE);
            request.getRequestDispatcher(ERROR_PAGE_PATH).forward(request, response);
        }
    }

}
