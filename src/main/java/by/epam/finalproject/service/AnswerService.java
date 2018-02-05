package by.epam.finalproject.service;

import by.epam.finalproject.entity.Answer;
import by.epam.finalproject.service.exception.ServiceException;


public interface AnswerService {

    void addAnswer(Answer answer) throws ServiceException;

}
