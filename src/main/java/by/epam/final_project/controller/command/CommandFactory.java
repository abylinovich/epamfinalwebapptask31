package by.epam.final_project.controller.command;

import by.epam.final_project.controller.command.impl.FindCommand;
import by.epam.final_project.controller.command.impl.LoginCommand;
import by.epam.final_project.controller.command.impl.RegistrationCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    private static final CommandFactory commandFactory = new CommandFactory();
    private static final Map<String, Command> commands = new HashMap<>();
    static {
        commands.put("login", new LoginCommand());
        commands.put("findUser", new FindCommand());
        commands.put("registration", new RegistrationCommand());
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
