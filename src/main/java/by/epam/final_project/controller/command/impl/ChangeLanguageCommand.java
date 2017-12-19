package by.epam.final_project.controller.command.impl;

import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ChangeLanguageCommand extends AbstractCommand {

    private final static Logger logger = Logger.getLogger(ChangeLanguageCommand.class);

    private final static String REQUEST_LANGUAGE_PARAMETER = "language";

    @Override
    public void doGet(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String language = request.getParameter(REQUEST_LANGUAGE_PARAMETER);
        request.getSession().setAttribute("language", language);
        String url = request.getParameter("url");
        response.sendRedirect(url);
    }

    @Override
    public void doPost(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new ServletException("Method POST is not supported.");
    }

}
