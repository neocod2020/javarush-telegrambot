package com.github.javarushcommunity.jrtb.service;

// класс, содержащий логику создания сообщений

import com.github.javarushcommunity.jrtb.bot.JavarushTelegramBot;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class SendBotMessageServiceImpl implements SendBotMessageService {
    
private final JavarushTelegramBot javarushBot;

    @Autowired
    public SendBotMessageServiceImpl(JavarushTelegramBot javarushBot) {
        this.javarushBot = javarushBot;
    }

    @Override
    public void sendMessage(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);
        sendMessage.setText(message);
        
    try {
        javarushBot.execute(sendMessage);
    } catch (TelegramApiException ex) {
       // Logger.getLogger(SendBotMessageServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
       ex.printStackTrace();
    }
    }
    
}
