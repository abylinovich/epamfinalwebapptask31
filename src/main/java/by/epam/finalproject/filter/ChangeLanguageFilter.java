package by.epam.finalproject.filter;

import org.apache.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.finalproject.controller.command.constant.HttpParameterName.LANGUAGE_PARAMETER_NAME;
import static by.epam.finalproject.controller.command.constant.HttpParameterName.REFERER_HEADER_NAME;


public class ChangeLanguageFilter implements Filter {

    private final static Logger logger = Logger.getLogger(ChangeLanguageFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String language = request.getParameter(LANGUAGE_PARAMETER_NAME);
        if(language != null) {
            request.getSession().setAttribute(LANGUAGE_PARAMETER_NAME, language);
            logger.debug("Change language to '" + language + "'.");
            String referer = request.getHeader(REFERER_HEADER_NAME);
            response.sendRedirect(referer);
            logger.debug("Redirect to '" + referer + "'.");
            return;
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}
