package by.epam.final_project.controller.command;

import by.epam.final_project.controller.command.impl.HomeCommand;
import by.epam.final_project.controller.command.impl.LoginCommand;
import by.epam.final_project.controller.command.impl.LogoutCommand;
import by.epam.final_project.controller.command.impl.RegisterCommand;

import java.util.HashMap;
import java.util.Map;

import static by.epam.final_project.controller.command.constant.HttpParameterName.*;

public class CommandResolver {

    private static final CommandResolver COMMAND_RESOLVER = new CommandResolver();
    private final Map<String, Command> commands = new HashMap<>();

    private CommandResolver() {
        commands.put(LOGIN_PARAMETER_NAME, new LoginCommand());
        commands.put(REGISTER_PARAMETER_NAME, new RegisterCommand());
        commands.put(HOME_PARAMETER_NAME, new HomeCommand());
        commands.put(LOGOUT_PARAMETER_NAME, new LogoutCommand());
    }

    public static CommandResolver getInstance() {
        return COMMAND_RESOLVER;
    }

    public Map<String, Command> getCommands() {
        return commands;
    }

    public Command getCommand(String commandName) {
        return commands.get(commandName);
    }

}
