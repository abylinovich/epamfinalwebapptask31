package by.epam.final_project.controller.command.impl;

import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.final_project.controller.command.message.HTTPParameterNameUtil.LANGUAGE_PARAMETER_NAME;
import static by.epam.final_project.controller.command.message.HTTPParameterNameUtil.URL_PARAMETER_NAME;


public class ChangeLanguageCommand extends AbstractCommand {

    private final static Logger logger = Logger.getLogger(ChangeLanguageCommand.class);


    @Override
    public void doGet(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String language = request.getParameter(LANGUAGE_PARAMETER_NAME);
        request.getSession().setAttribute(LANGUAGE_PARAMETER_NAME, language);
        String url = request.getParameter(URL_PARAMETER_NAME);
        response.sendRedirect(url);
        logger.debug("Change language to '" + language + "'.");
    }

    @Override
    public void doPost(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new ServletException("Method POST is not supported.");
    }

}
