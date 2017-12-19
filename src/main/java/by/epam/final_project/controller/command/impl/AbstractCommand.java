package by.epam.final_project.controller.command.impl;

import by.epam.final_project.controller.command.Command;
import by.epam.final_project.service.exception.ServiceException;
import by.epam.final_project.service.UserService;
import by.epam.final_project.service.UserServiceFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.final_project.controller.command.message.FrontMessageUtil.SERVER_ERROR_MESSAGE;
import static by.epam.final_project.controller.command.message.HTTPParameterNameUtil.ERROR_MESSAGE_PARAMETER_NAME;
import static by.epam.final_project.controller.command.message.PagePathUtil.ERROR_PAGE_PATH;

public abstract class AbstractCommand implements Command {

    protected UserService userService = UserServiceFactory.getInstance().getUserService();

    @Override
    public void init() throws ServletException {
    }

}
