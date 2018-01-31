package by.epam.final_project.service;

import by.epam.final_project.entity.Question;
import by.epam.final_project.service.exception.ServiceException;

import java.util.List;

public interface QuestionService {

    List<Question> getRandomQuestion() throws ServiceException;

    int getQuestionsCount() throws ServiceException;

    List<Question> getQuestions(String page, String count) throws ServiceException;

    Question getQuestion(String id) throws ServiceException;

    List<Question> getQuestionsByUser(String id, String page, String count) throws ServiceException;

    int getQuestionsCountForUser(String id) throws ServiceException;

    void addQuestion(Question question) throws ServiceException;

}
