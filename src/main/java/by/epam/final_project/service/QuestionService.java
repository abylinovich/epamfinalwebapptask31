package by.epam.final_project.service;

import by.epam.final_project.entity.Question;
import by.epam.final_project.service.exception.ServiceException;

public interface QuestionService {

    Question getRandomQuestion() throws ServiceException;

}
