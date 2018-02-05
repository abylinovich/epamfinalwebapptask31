package by.epam.finalproject.controller;

import by.epam.finalproject.controller.command.Command;
import by.epam.finalproject.controller.command.CommandResolver;
import by.epam.finalproject.dao.connectionpool.ConnectionPool;
import by.epam.finalproject.dao.exception.ConnectionPoolException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.finalproject.controller.command.constant.HttpParameterName.BAD_REQUEST_PARAMETER_ERROR_ATTRIBUTE_NAME;
import static by.epam.finalproject.controller.command.constant.HttpParameterName.COMMAND_PARAMETER_NAME;
import static by.epam.finalproject.controller.command.constant.PagePath.ERROR_PAGE_PATH;

public class FrontController extends HttpServlet {

    private static final long serialVersionUID = -1131956170161219954L;

    private Logger logger = Logger.getLogger(FrontController.class);

    private CommandResolver commandResolver = CommandResolver.getInstance();


    @Override
    public void init() throws ServletException {
        try {
            ConnectionPool.getConnectionPool().init();
        } catch (ConnectionPoolException e) {
            throw new ServletException("Initialization exception. Cannot initialize connection pool.", e);
        }
        logger.info("Servlet has been successfully initialized.");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = getCommand(request);
        if(command != null) {
            command.doGet(request, response);
        } else {
            request.setAttribute(BAD_REQUEST_PARAMETER_ERROR_ATTRIBUTE_NAME, true);
            request.getRequestDispatcher(ERROR_PAGE_PATH).forward(request, response);
            logger.debug("Command not found. Forward to error page.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = getCommand(request);
        if(command != null) {
            command.doPost(request, response);
        } else {
            request.setAttribute(BAD_REQUEST_PARAMETER_ERROR_ATTRIBUTE_NAME, true);
            request.getRequestDispatcher(ERROR_PAGE_PATH).forward(request, response);
            logger.debug("Command not found. Forward to error page.");
        }
    }

    @Override
    public void destroy() {
        ConnectionPool.getConnectionPool().destroy();
        logger.info("Servlet has been successfully destroyed.");
    }

    private Command getCommand(HttpServletRequest request) throws ServletException {
        String commandName = request.getParameter(COMMAND_PARAMETER_NAME);
        Command command = commandResolver.getCommand(commandName);
        return command;
    }

}
