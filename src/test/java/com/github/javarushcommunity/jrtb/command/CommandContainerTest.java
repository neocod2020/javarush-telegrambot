package com.github.javarushcommunity.jrtb.command;


import com.github.javarushcommunity.jrtb.service.SendBotMessageService;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@DisplayName("Unit-level testing for CommandContainer")
class CommandContainerTest {
    
    private CommandContainer commandContainer;
    
    @BeforeEach
    public void init() {
        SendBotMessageService sendBotMessageService = Mockito.mock(SendBotMessageService.class);
        commandContainer = new CommandContainer(sendBotMessageService);
    }
	@Test
	void shouldGetAllExistingCommands() {
            //when - then
            Arrays.stream(CommandName.values())
                    .forEach(commandName -> {
                    Command command = commandContainer.retrieveCommand(commandName.getCommandName());
                    Assertions.assertNotEquals(UnknownCommand.class, command.getClass());
                    });
	}
        @Test
	void shouldReturnUnknownCommand() {
            //given
            String unknownCommand = "/qwerty";
            //when
            Command command = commandContainer.retrieveCommand(unknownCommand);
            //then
            Assertions.assertEquals(UnknownCommand.class, command.getClass());                    
	}
}
