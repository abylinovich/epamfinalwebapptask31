package by.epam.final_project.controller.command;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {

    void process(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response);

}
