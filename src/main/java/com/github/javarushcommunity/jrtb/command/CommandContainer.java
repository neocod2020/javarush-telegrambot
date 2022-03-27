package com.github.javarushcommunity.jrtb.command;


import static com.github.javarushcommunity.jrtb.command.CommandName.*;
import com.github.javarushcommunity.jrtb.service.SendBotMessageService;
import com.google.common.collect.ImmutableMap;

public class CommandContainer {
    private final ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;
    
    public CommandContainer(SendBotMessageService sendBotMessageService) {
    // создаем неизменяемую мапу, содержащую наименование команды и команду    
        commandMap = ImmutableMap.<String, Command>builder()
                .put(START.getCommandName(), new StartCommand(sendBotMessageService))
                .put(STOP.getCommandName(), new StopCommand(sendBotMessageService))
                .put(HELP.getCommandName(), new HelpCommand(sendBotMessageService))
                .put(NO.getCommandName(), new NoCommand(sendBotMessageService))
                .build();
        unknownCommand = new UnknownCommand(sendBotMessageService);
    }
    public Command retrieveCommand(String commandIdenifier){
        // возвращается команда по ключу, или unknownCommand если в карте нет ключа
        return commandMap.getOrDefault(commandIdenifier, unknownCommand);
    }
    
}
