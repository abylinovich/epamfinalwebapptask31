package by.epam.final_project.controller.command.impl;

import by.epam.final_project.entity.User;
import by.epam.final_project.service.exception.ServiceException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.final_project.controller.command.message.FrontMessageUtil.CANNOT_REGISTER_USER_MESSAGE;
import static by.epam.final_project.controller.command.message.FrontMessageUtil.USER_NOT_FOUND_MESSAGE;
import static by.epam.final_project.controller.command.message.HTTPParameterNameUtil.*;
import static by.epam.final_project.controller.command.message.PagePathUtil.ERROR_PAGE_PATH;
import static by.epam.final_project.controller.command.message.PagePathUtil.HOME_PAGE_PATH;

public class RegistrationCommand extends AbstractCommand {

    @Override
    public void doGet(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new ServletException("Method GET is not supported.");
    }

    @Override
    public void doPost(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(LOGIN_PARAMETER_NAME);
        String password = request.getParameter(PASSWORD_PARAMETER_NAME);
        String firstName = request.getParameter(FIRST_NAME_PARAMETER_NAME);
        String lastName = request.getParameter(LAST_NAME_PARAMETER_NAME);
        String email = request.getParameter(EMAIL_PARAMETER_NAME);
        int age = Integer.parseInt(request.getParameter(AGE_PARAMETER_NAME));
        try {
            User user = userService.createNewUser(login, password, firstName, lastName, email, age);
            if(user != null) {
                request.setAttribute(USER_PARAMETER_NAME, user);
                request.getRequestDispatcher(HOME_PAGE_PATH).forward(request, response);
            } else {
                request.setAttribute(ERROR_MESSAGE_PARAMETER_NAME, USER_NOT_FOUND_MESSAGE);
            }
        } catch (ServiceException e) {
            request.setAttribute(ERROR_MESSAGE_PARAMETER_NAME, CANNOT_REGISTER_USER_MESSAGE);
        }
        request.getRequestDispatcher(ERROR_PAGE_PATH).forward(request, response);
    }

}
