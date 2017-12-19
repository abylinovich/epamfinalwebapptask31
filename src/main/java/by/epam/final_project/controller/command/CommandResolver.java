package by.epam.final_project.controller.command;

import by.epam.final_project.controller.command.impl.LoginCommand;
import by.epam.final_project.controller.command.impl.RegistrationCommand;

import java.util.HashMap;
import java.util.Map;

import static by.epam.final_project.controller.command.message.HTTPParameterNameUtil.LOGIN_PARAMETER_NAME;
import static by.epam.final_project.controller.command.message.HTTPParameterNameUtil.REGISTRATION_PARAMETER_NAME;

public class CommandResolver {

    private static final CommandResolver COMMAND_RESOLVER = new CommandResolver();
    private final Map<String, Command> commands = new HashMap<>();

    private CommandResolver() {
        commands.put(LOGIN_PARAMETER_NAME, new LoginCommand());
        commands.put(REGISTRATION_PARAMETER_NAME, new RegistrationCommand());
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
