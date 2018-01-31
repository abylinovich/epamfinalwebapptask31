package by.epam.final_project.controller.command;

import javax.servlet.http.HttpServletRequest;

public interface Paginator {

    String checkParameter(HttpServletRequest request, String parameter);

    void setParameter(HttpServletRequest request, String parameter);

}
