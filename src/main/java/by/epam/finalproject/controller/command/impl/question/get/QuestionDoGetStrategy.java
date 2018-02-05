package by.epam.finalproject.controller.command.impl.question.get;

import by.epam.finalproject.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface QuestionDoGetStrategy {

    void processGet(HttpServletRequest request, HttpServletResponse response) throws ServiceException;

}
