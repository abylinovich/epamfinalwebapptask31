package by.epam.final_project.controller.filter;

import by.epam.final_project.controller.command.Paginator;
import by.epam.final_project.controller.command.PaginatorFactory;
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

import static by.epam.final_project.controller.command.constant.HttpParameterName.*;

public class PaginationFilter implements Filter {

    private final static Logger logger = Logger.getLogger(PaginationFilter.class);

    private Paginator paginator = PaginatorFactory.getInstance().getPaginator();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        paginator.setParameter(request, PAGE_PARAMETER_NAME);
        paginator.setParameter(request, COUNT_PARAMETER_NAME);

        if(request.getAttribute(PROCESSED_ATTRIBUTE_NAME) != null) {
            response.sendRedirect(request.getRequestURI() + "?" + request.getAttribute(QUERY_ATTRIBUTE_NAME));
            logger.debug("URL processing complete. Redirect.");
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }


}
