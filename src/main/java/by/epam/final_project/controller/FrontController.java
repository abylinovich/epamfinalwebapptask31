package by.epam.final_project.controller;

import by.epam.final_project.controller.command.Command;
import by.epam.final_project.controller.command.CommandResolver;
import by.epam.final_project.dao.connectionpool.ConnectionPool;
import by.epam.final_project.dao.exception.ConnectionPoolException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.final_project.controller.command.constant.HttpParameterName.COMMAND_PARAMETER_NAME;

public class FrontController extends HttpServlet {

    private static final long serialVersionUID = -1131956170161219954L;

    private Logger logger = Logger.getLogger(FrontController.class);


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
        command.doGet(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = getCommand(request);
        command.doPost(request, response);
    }

    @Override
    public void destroy() {
        ConnectionPool.getConnectionPool().destroy();
        logger.info("Servlet has been successfully destroyed.");
    }

    private Command getCommand(HttpServletRequest request) throws ServletException {
        String commandName = request.getParameter(COMMAND_PARAMETER_NAME);
        Command command = CommandResolver.getInstance().getCommand(commandName);
        if(command == null) {
            throw new ServletException("No such command found.");
        }
        return command;
    }

}
