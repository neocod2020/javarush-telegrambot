package com.github.javarushcommunity.jrtb.command;


public enum CommandName {
    
    START("/start"),
    HELP("/help"),
    NO("nocommand"),
    STOP("/stop");
    
    private final String commandName;

    private CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }    
    
}
