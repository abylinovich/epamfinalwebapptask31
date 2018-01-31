package by.epam.final_project.controller.command;

import by.epam.final_project.service.validator.ParameterValidator;
import by.epam.final_project.service.validator.ValidatorFactory;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

import static by.epam.final_project.controller.command.constant.HttpParameterName.COUNT_PARAMETER_NAME;
import static by.epam.final_project.controller.command.constant.HttpParameterName.PAGE_PARAMETER_NAME;
import static by.epam.final_project.controller.command.constant.HttpParameterName.QUERY_ATTRIBUTE_NAME;
import static by.epam.final_project.controller.command.constant.HttpParameterName.PROCESSED_ATTRIBUTE_NAME;


public class PaginatorImpl implements Paginator {

    private final static Logger logger = Logger.getLogger(PaginatorImpl.class);

    private ParameterValidator parameterValidator = ValidatorFactory.getInstance().getParameterValidator();
    private Map<String, String> basicValues = new HashMap<>();

    private static final String BASIC_PAGE = "1";
    private static final String BASIC_COUNT = "5";

    public PaginatorImpl() {
        basicValues.put(PAGE_PARAMETER_NAME, BASIC_PAGE);
        basicValues.put(COUNT_PARAMETER_NAME, BASIC_COUNT);
    }

    @Override
    public String checkParameter(HttpServletRequest request, String parameter) {
        String value = (String) request.getSession().getAttribute(parameter);
        if(value == null) {
            value = basicValues.get(parameter);
            request.getSession().setAttribute(parameter, value);
            logger.debug("Set session attribute " + parameter + " = " + value);
        }
        return value;
    }

    @Override
    public void setParameter(HttpServletRequest request, String parameter) {
        String value = request.getParameter(parameter);
        if(value != null) {
            if(parameterValidator.validateNumeric(value)) {
                request.getSession().setAttribute(parameter, value);
                logger.debug("Set session parameter '" + parameter + "' = " + value);

            }
            String query = (String) request.getAttribute(QUERY_ATTRIBUTE_NAME);
            if(query == null) {
                query = request.getQueryString();
            }
            String newQuery = query.replaceAll("&" + parameter + "=.+&?", "");
            request.setAttribute(QUERY_ATTRIBUTE_NAME, newQuery);
            request.setAttribute(PROCESSED_ATTRIBUTE_NAME, true);
        }
    }

}
