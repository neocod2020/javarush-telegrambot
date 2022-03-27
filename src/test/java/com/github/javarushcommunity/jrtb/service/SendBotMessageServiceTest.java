package com.github.javarushcommunity.jrtb.service;

import com.github.javarushcommunity.jrtb.bot.JavarushTelegramBot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@DisplayName("Unit-level testing for SendBotMessageService")
public class SendBotMessageServiceTest {
    
    private SendBotMessageService sendBotMessageService;
    private JavarushTelegramBot javarushBot;
    
    @BeforeEach
    public void init() {
        javarushBot = Mockito.mock(JavarushTelegramBot.class);
        sendBotMessageService = new SendBotMessageServiceImpl(javarushBot);
    }
    
    @Test
    public void shouldProperlySendMessage() throws TelegramApiException{
        //given
        String message = "test_message";
        String chatId =  "test_chat_id";
        
        SendMessage sm = new SendMessage(); 
           sm.setText(message);
           sm.setChatId(chatId);
           sm.enableHtml(true);
           
           //when
           sendBotMessageService.sendMessage(chatId, message);
           //then
           Mockito.verify(javarushBot).execute(sm);
    }
}
