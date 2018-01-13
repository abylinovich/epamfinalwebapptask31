package by.epam.final_project.controller.command.impl.question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface QuestionStrategy {

    void setPageContent(HttpServletRequest request, HttpServletResponse response);

}
