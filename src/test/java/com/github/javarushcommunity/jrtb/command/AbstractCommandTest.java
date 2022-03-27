package com.github.javarushcommunity.jrtb.command;


import com.github.javarushcommunity.jrtb.bot.JavarushTelegramBot;
import com.github.javarushcommunity.jrtb.service.SendBotMessageService;
import com.github.javarushcommunity.jrtb.service.SendBotMessageServiceImpl;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

abstract class AbstractCommandTest {
    
    protected JavarushTelegramBot javarushBot =
            Mockito.mock(JavarushTelegramBot.class);
    protected SendBotMessageService sendBotMessageService =
            new SendBotMessageServiceImpl(javarushBot);    
    
    abstract String getCommandName();
    abstract String getCommandMessage();
    abstract Command getCommand();
    
	@Test
	void shouldProperlyExecuteCommand() throws TelegramApiException {
            //given
            Long chatId = 1234567824356L;
            Update update = new Update();
            Message message = Mockito.mock(Message.class);
            Mockito.when(message.getChatId()).thenReturn(chatId);
            Mockito.when(message.getText()).thenReturn(getCommandName());
            update.setMessage(message);
            
            SendMessage sm = new SendMessage(); 
           sm.setText(getCommandMessage());
           sm.setChatId(chatId.toString());
           sm.enableHtml(true);
           
           //when
           getCommand().execute(update);
           
           //then
           Mockito.verify(javarushBot).execute(sm);
            
	}        
}
