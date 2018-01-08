package by.epam.final_project.controller.command.impl;

import by.epam.final_project.controller.command.Command;
import by.epam.final_project.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.final_project.controller.command.constant.HttpParameterName.USER_PARAMETER_NAME;
import static by.epam.final_project.controller.command.constant.PagePath.LOGIN_PAGE_PATH;

public class LogoutCommand implements Command {

    private final static Logger logger = Logger.getLogger(LogoutCommand.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect(LOGIN_PAGE_PATH);
        logger.debug("Logout successful. Redirect to login page.");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new ServletException("Method POST is not available for this command.");
    }

}
