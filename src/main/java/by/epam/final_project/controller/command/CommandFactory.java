package by.epam.final_project.controller.command;

import by.epam.final_project.controller.command.impl.LoginCommand;
import by.epam.final_project.controller.command.impl.RegistrationCommand;

import java.util.HashMap;
import java.util.Map;

import static by.epam.final_project.controller.command.message.HTTPParameterNameUtil.LOGIN_PARAMETER_NAME;
import static by.epam.final_project.controller.command.message.HTTPParameterNameUtil.REGISTRATION_PARAMETER_NAME;

public class CommandFactory {

    private static final CommandFactory commandFactory = new CommandFactory();
    private static final Map<String, Command> commands = new HashMap<>();
    static {
        commands.put(LOGIN_PARAMETER_NAME, new LoginCommand());
        commands.put(REGISTRATION_PARAMETER_NAME, new RegistrationCommand());
    }

    private CommandFactory() {
    }

    public static CommandFactory getInstance() {
        return commandFactory;
    }

    public static Map<String, Command> getCommands() {
        return commands;
    }

    public static Command getCommand(String commandName) {
        return commands.get(commandName);
    }

}
