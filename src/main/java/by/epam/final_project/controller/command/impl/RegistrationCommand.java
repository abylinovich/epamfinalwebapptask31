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

public class RegistrationCommand implements Command {

    private UserService userService = UserServiceFactory.getUserService();
    private static final String ERROR_MESSAGE = "Cannot register user. Please, restart page.";

    @Override
    public void process(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String email = request.getParameter("email");
        int age = Integer.parseInt(request.getParameter("age"));
        try {
            userService.createNewUser(login, password, firstName, lastName, email, age);
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        } catch (ServiceException e) {
            request.setAttribute("errorMessage", ERROR_MESSAGE);
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }

}
