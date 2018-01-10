package by.epam.final_project.service;

import by.epam.final_project.entity.Question;
import by.epam.final_project.service.exception.ServiceException;

import java.util.List;

public interface QuestionService {

    List<Question> getRandomQuestion() throws ServiceException;

    List<Question> getQuestions() throws ServiceException;

    List<Question> getQuestion(String id) throws ServiceException;

    List<Question> getQuestionsByUser(String id) throws ServiceException;

}
