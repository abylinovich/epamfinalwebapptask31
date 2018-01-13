package by.epam.final_project.controller.command;

import by.epam.final_project.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

import static by.epam.final_project.controller.command.constant.HttpParameterName.*;

public class CommandResolver {

    private static final CommandResolver COMMAND_RESOLVER = new CommandResolver();
    private final Map<String, Command> commands = new HashMap<>();

    private CommandResolver() {
        commands.put(LOGIN_COMMAND_NAME, new LoginCommand());
        commands.put(REGISTER_COMMAND_NAME, new RegisterCommand());
        commands.put(LOGOUT_COMMAND_NAME, new LogoutCommand());
        commands.put(QUESTION_COMMAND_NAME, new QuestionCommand());
        commands.put(USER_COMMAND_NAME, new UserCommand());
    }

    public static CommandResolver getInstance() {
        return COMMAND_RESOLVER;
    }

    public Command getCommand(String commandName) {
        return commands.get(commandName);
    }

}
