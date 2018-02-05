package by.epam.finalproject.filter;

import org.apache.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import static by.epam.finalproject.controller.command.constant.HttpParameterName.COMMAND_PARAMETER_NAME;
import static by.epam.finalproject.controller.command.constant.HttpParameterName.REGISTER_PARAMETER_NAME;
import static by.epam.finalproject.controller.command.constant.HttpParameterName.USER_PARAMETER_NAME;
import static by.epam.finalproject.controller.command.constant.HttpParameterName.LOGIN_PARAMETER_NAME;
import static by.epam.finalproject.controller.command.constant.PagePath.LOGIN_PAGE_PATH;
import static by.epam.finalproject.controller.command.constant.PagePath.REGISTER_PAGE_PATH;

public class AuthenticationFilter implements Filter {

    private final static Logger logger = Logger.getLogger(AuthenticationFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();
        String command = request.getParameter(COMMAND_PARAMETER_NAME);
        HttpSession session = request.getSession(false);

        if(session != null && session.getAttribute(USER_PARAMETER_NAME) != null) {
            logger.debug("User is authenticated. Next filter do.");
            filterChain.doFilter(request, response);
            return;
        }

        if((uri.equals(LOGIN_PAGE_PATH) || uri.equals(REGISTER_PAGE_PATH)) ||
                command == null ||
                command.equals(LOGIN_PARAMETER_NAME) ||
                command.equals(REGISTER_PARAMETER_NAME)) {

            logger.debug("Anonymous user location is login or register page. Next filter do.");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        logger.debug("User is not authenticated. Redirect to login page.");
        response.sendRedirect(LOGIN_PAGE_PATH);
    }

    @Override
    public void destroy() {
    }
}
