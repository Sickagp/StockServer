package cz.schutzpetr.stock.server.commands;

import cz.schutzpetr.stock.server.commands.annotation.BaseCommand;
import cz.schutzpetr.stock.server.commands.annotation.Command;
import cz.schutzpetr.stock.server.commands.interfaces.CommandClass;
import cz.schutzpetr.stock.server.commands.utils.CommandContainer;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;

/**
 * Created by Petr Schutz on 17.03.2017
 *
 * @author Petr Schutz
 * @version 1.0
 */
public class CommandManager {
    /**
     * This {@code LinkedHashMap} stores a list of methods for individual commands.
     */
    private static LinkedHashMap<String, LinkedHashMap<String, CommandContainer>> commands = new LinkedHashMap<>();

    /**
     * This {@code LinkedHashMap} stores a base commands
     */
    private static LinkedHashMap<String, BaseCommand> baseCommands = new LinkedHashMap<>();

    /**
     * Create instance of CommandManager.
     */
    private CommandManager() {
    }

    public static void registerCommands() {
        registerCommands(LocationCommands.class);
    }


    /**
     * This method registers all the commands of a given class
     */
    public static void registerCommands(Class<? extends CommandClass> commandClass) {
        Method[] methods = commandClass.getMethods();
        Method[] arrayOfMethod1;
        int j = (arrayOfMethod1 = methods).length;
        BaseCommand baseCommand = commandClass.getAnnotation(BaseCommand.class);
        LinkedHashMap<String, CommandContainer> subCommands = commands.computeIfAbsent(baseCommand.command(), k -> new LinkedHashMap<>());
        baseCommands.putIfAbsent(baseCommand.command(), baseCommand);
        for (int i = 0; i < j; i++) {
            Method method = arrayOfMethod1[i];
            if (method.isAnnotationPresent(Command.class)) {
                Command command = method.getAnnotation(Command.class);

                subCommands.put(command.aliases()[0], new CommandContainer(command, method));
            }
        }
    }

    public static CommandContainer getCommandContainer(String command, String[] args) {
        if (commands.containsKey(command) && commands.get(command).containsKey(args[0])) {
            return commands.get(command).get(args[0]);
        } else {
            return null;
        }
    }

    public static BaseCommand getBaseCommand(String baseCommand) {
        return baseCommands.get(baseCommand);
    }

    /**
     * Calls the given command
     *
     * @param commandContainer container, that contains instances of {@code Command} and {@code Method}
     * @param args             command args
     */
    public static void dispatchCommand(CommandContainer commandContainer, Object... args) {

        try {
            System.out.println(args.length);
            commandContainer.getMethod().invoke(null, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
