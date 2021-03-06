package by.epam.finalproject.controller.command.impl;

import by.epam.finalproject.controller.command.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.finalproject.controller.command.constant.HttpParameterName.MESSAGE_ERROR_ATTRIBUTE_NAME;
import static by.epam.finalproject.controller.command.constant.PagePath.ERROR_PAGE_PATH;
import static by.epam.finalproject.controller.command.constant.PagePath.LOGIN_PAGE_PATH;


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
        request.setAttribute(MESSAGE_ERROR_ATTRIBUTE_NAME, true);
        request.getRequestDispatcher(ERROR_PAGE_PATH).forward(request, response);
        logger.debug("Method POST is not available for this command.");
    }

}
