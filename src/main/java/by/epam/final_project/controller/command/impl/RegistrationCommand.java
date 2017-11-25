package by.epam.final_project.controller.command.impl;

import by.epam.final_project.controller.command.Command;
import by.epam.final_project.exception.ServiceException;
import by.epam.final_project.service.UserService;
import by.epam.final_project.service.UserServiceFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.final_project.controller.command.message.FrontMessageUtil.CANNOT_REGISTER_USER_MESSAGE;
import static by.epam.final_project.controller.command.message.PagePathUtil.ERROR_PAGE_PATH;
import static by.epam.final_project.controller.command.message.PagePathUtil.LOGIN_PAGE_PATH;
import static by.epam.final_project.controller.command.message.HTTPParameterNameUtil.LOGIN_PARAMETER_NAME;
import static by.epam.final_project.controller.command.message.HTTPParameterNameUtil.PASSWORD_PARAMETER_NAME;
import static by.epam.final_project.controller.command.message.HTTPParameterNameUtil.FIRST_NAME_PARAMETER_NAME;
import static by.epam.final_project.controller.command.message.HTTPParameterNameUtil.LAST_NAME_PARAMETER_NAME;
import static by.epam.final_project.controller.command.message.HTTPParameterNameUtil.EMAIL_PARAMETER_NAME;
import static by.epam.final_project.controller.command.message.HTTPParameterNameUtil.AGE_PARAMETER_NAME;
import static by.epam.final_project.controller.command.message.HTTPParameterNameUtil.ERROR_MESSAGE_PARAMETER_NAME;

public class RegistrationCommand implements Command {

    private UserService userService = UserServiceFactory.getInstance().getUserService();

    @Override
    public void process(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(LOGIN_PARAMETER_NAME);
        String password = request.getParameter(PASSWORD_PARAMETER_NAME);
        String firstName = request.getParameter(FIRST_NAME_PARAMETER_NAME);
        String lastName = request.getParameter(LAST_NAME_PARAMETER_NAME);
        String email = request.getParameter(EMAIL_PARAMETER_NAME);
        int age = Integer.parseInt(request.getParameter(AGE_PARAMETER_NAME));
        try {
            userService.createNewUser(login, password, firstName, lastName, email, age);
            request.getRequestDispatcher(LOGIN_PAGE_PATH).forward(request, response);
        } catch (ServiceException e) {
            request.setAttribute(ERROR_MESSAGE_PARAMETER_NAME, CANNOT_REGISTER_USER_MESSAGE);
            request.getRequestDispatcher(ERROR_PAGE_PATH).forward(request, response);
        }
    }

}
