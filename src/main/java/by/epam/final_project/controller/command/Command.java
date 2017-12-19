package by.epam.final_project.controller.command;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {

    void init() throws ServletException;

    void doGet(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    void doPost(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
