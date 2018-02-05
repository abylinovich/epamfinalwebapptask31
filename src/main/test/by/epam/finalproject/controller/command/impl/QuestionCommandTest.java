package by.epam.finalproject.controller.command.impl;

import by.epam.finalproject.controller.command.Command;
import by.epam.finalproject.controller.command.impl.question.get.impl.AllQuestionsStrategy;
import by.epam.finalproject.controller.command.impl.question.get.QuestionDoGetStrategy;
import by.epam.finalproject.controller.command.impl.question.get.QuestionDoGetFactory;
import org.junit.Before;
import org.junit.Test;

import static by.epam.finalproject.controller.command.constant.HttpParameterName.DO_PARAMETER_NAME;
import static by.epam.finalproject.controller.command.constant.PagePath.ERROR_PAGE_PATH;
import static by.epam.finalproject.controller.command.constant.PagePath.MAIN_PAGE_PATH;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.mockito.internal.util.reflection.Whitebox;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class QuestionCommandTest {

    private Command command;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher requestDispatcher;


    @Before
    public void setUp() throws Exception {
        command = new QuestionCommand();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        requestDispatcher = mock(RequestDispatcher.class);
    }

    @Test
    public void test_no_param() throws ServletException, IOException {
        when(request.getParameter(DO_PARAMETER_NAME)).thenReturn(null);
        when(request.getRequestDispatcher(ERROR_PAGE_PATH)).thenReturn(requestDispatcher);

        command.doGet(request, response);

        verify(request).getParameter(DO_PARAMETER_NAME);
        verify(request).getRequestDispatcher(ERROR_PAGE_PATH);
        verify(requestDispatcher).forward(request, response);
        verifyNoMoreInteractions(request, response);
    }

    @Test
    public void test_bad_param() throws ServletException, IOException {
        when(request.getParameter(DO_PARAMETER_NAME)).thenReturn("test");
        when(request.getRequestDispatcher(ERROR_PAGE_PATH)).thenReturn(requestDispatcher);

        command.doGet(request, response);

        verify(request).getParameter(DO_PARAMETER_NAME);
        verify(request).getRequestDispatcher(ERROR_PAGE_PATH);
        verify(requestDispatcher).forward(request, response);
        verifyNoMoreInteractions(request, response);
    }

    @Test
    public void test_ok() throws ServletException, IOException {
        QuestionDoGetFactory strategyResolver = mock(QuestionDoGetFactory.class);
        QuestionDoGetStrategy questionStrategy = mock(AllQuestionsStrategy.class);

        when(request.getParameter(DO_PARAMETER_NAME)).thenReturn("all");
        when(request.getRequestDispatcher(MAIN_PAGE_PATH)).thenReturn(requestDispatcher);
        when(strategyResolver.getStrategy("all")).thenReturn(questionStrategy);
        Whitebox.setInternalState(command, "strategyResolver", strategyResolver);

        command.doGet(request, response);

        verify(request).getParameter(DO_PARAMETER_NAME);
        verify(request).getRequestDispatcher(MAIN_PAGE_PATH);
        verify(requestDispatcher).forward(request, response);
        verifyNoMoreInteractions(request, response);
    }

}
