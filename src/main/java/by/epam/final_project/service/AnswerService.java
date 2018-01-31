package by.epam.final_project.service;

import by.epam.final_project.entity.Answer;
import by.epam.final_project.service.exception.ServiceException;

public interface AnswerService {

    void addAnswer(Answer answer) throws ServiceException;

}
