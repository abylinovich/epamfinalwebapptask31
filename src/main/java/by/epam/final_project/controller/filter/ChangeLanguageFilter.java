package by.epam.final_project.controller.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.final_project.controller.command.constant.HttpParameterName.*;
import static by.epam.final_project.controller.command.constant.PagePath.LOGIN_PAGE_PATH;
import static by.epam.final_project.controller.command.constant.PagePath.REGISTER_PAGE_PATH;

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
            String referer = request.getHeader(REFERER_HEADER_NAME);
            response.sendRedirect(referer);
            logger.debug("Change language to '" + language + "'.");
            return;
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}
