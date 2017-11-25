package by.epam.final_project.controller;

import by.epam.final_project.controller.command.CommandFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.final_project.controller.command.message.HTTPParameterNameUtil.COMMAND_PARAMETER_NAME;

public class FrontController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter(COMMAND_PARAMETER_NAME);
        CommandFactory.getCommand(commandName).process(getServletContext(),request, response);
    }

}
