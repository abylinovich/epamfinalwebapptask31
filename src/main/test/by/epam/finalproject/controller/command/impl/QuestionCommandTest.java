package by.epam.finalproject.controller.command.impl;

import by.epam.finalproject.controller.command.Command;
import by.epam.finalproject.controller.command.CommandResolver;
import by.epam.finalproject.controller.command.impl.question.AllQuestionsStrategy;
import by.epam.finalproject.controller.command.impl.question.QuestionStrategy;
import by.epam.finalproject.controller.command.impl.question.QuestionStrategyResolver;
import org.junit.Before;
import org.junit.Test;

import static by.epam.finalproject.controller.command.constant.HttpParameterName.DO_PARAMETER_NAME;
import static by.epam.finalproject.controller.command.constant.PagePath.ERROR_PAGE_PATH;
import static by.epam.finalproject.controller.command.constant.PagePath.MAIN_PAGE_PATH;
import static org.mockito.Mockito.*;
import org.mockito.internal.util.reflection.Whitebox;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class QuestionCommandTest {

    private Command command;


    @Before
    public void setUp() throws Exception {
        command = new QuestionCommand();
    }

    @Test
    public void test_no_param() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

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
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

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
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        QuestionStrategyResolver strategyResolver = mock(QuestionStrategyResolver.class);
        QuestionStrategy questionStrategy = mock(AllQuestionsStrategy.class);


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
