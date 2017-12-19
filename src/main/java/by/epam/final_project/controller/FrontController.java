package by.epam.final_project.controller;

import by.epam.final_project.controller.command.Command;
import by.epam.final_project.controller.command.CommandResolver;
import by.epam.final_project.dao.connectionpool.ConnectionPoolFactory;
import by.epam.final_project.dao.exception.ConnectionPoolException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.final_project.controller.command.message.HTTPParameterNameUtil.COMMAND_PARAMETER_NAME;

public class FrontController extends HttpServlet {

    private static final long serialVersionUID = -1131956170161219954L;


    @Override
    public void init() throws ServletException {
        try {
            ConnectionPoolFactory.getInstance().init();
        } catch (ConnectionPoolException e) {
            throw new ServletException("Initialization exception. Cannot initialize connection pool.", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = getCommand(request);
        command.doGet(getServletContext(), request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Command command = getCommand(request);
        command.doPost(getServletContext(), request, response);
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
