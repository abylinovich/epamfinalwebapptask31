package by.epam.finalproject.controller.command.impl.question.post;

import by.epam.finalproject.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface QuestionDoPostStrategy {

    void processPost(HttpServletRequest request, HttpServletResponse response) throws ServiceException;

}
