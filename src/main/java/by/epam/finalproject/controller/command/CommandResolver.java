package by.epam.finalproject.controller.command;

import by.epam.finalproject.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

import static by.epam.finalproject.controller.command.constant.HttpParameterName.LOGIN_COMMAND_NAME;
import static by.epam.finalproject.controller.command.constant.HttpParameterName.LOGOUT_COMMAND_NAME;
import static by.epam.finalproject.controller.command.constant.HttpParameterName.REGISTER_COMMAND_NAME;
import static by.epam.finalproject.controller.command.constant.HttpParameterName.ANSWER_COMMAND_NAME;
import static by.epam.finalproject.controller.command.constant.HttpParameterName.QUESTION_COMMAND_NAME;
import static by.epam.finalproject.controller.command.constant.HttpParameterName.USER_COMMAND_NAME;


public class CommandResolver {

    private static final CommandResolver COMMAND_RESOLVER = new CommandResolver();
    private final Map<String, Command> commands = new HashMap<>();

    private CommandResolver() {
        commands.put(LOGIN_COMMAND_NAME, new LoginCommand());
        commands.put(REGISTER_COMMAND_NAME, new RegisterCommand());
        commands.put(LOGOUT_COMMAND_NAME, new LogoutCommand());
        commands.put(QUESTION_COMMAND_NAME, new QuestionCommand());
        commands.put(ANSWER_COMMAND_NAME, new AnswerCommand());
        commands.put(USER_COMMAND_NAME, new UserCommand());
    }

    public static CommandResolver getInstance() {
        return COMMAND_RESOLVER;
    }

    public Command getCommand(String commandName) {
        return commands.get(commandName);
    }

}
