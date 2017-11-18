package by.epam.final_project.controller;

import by.epam.final_project.controller.command.CommandFactory;
import by.epam.final_project.entity.User;
import by.epam.final_project.service.UserServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
        System.out.println("FrontController: doGet()");
//        req.getRequestDispatcher("/jsp/home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        super.doPost(req, resp);
        System.out.println("FrontController: doPost()");
        String commandName = request.getParameter("command");
        CommandFactory.getCommand(commandName).process(getServletContext(),request, response);
    }

}
