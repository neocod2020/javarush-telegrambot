package com.github.javarushcommunity.jrtb.bot;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class JavarushTelegramBot extends TelegramLongPollingBot {
    
    @Value("${bot.token}")
    private String token;
    
    @Value("${bot.username}")
    private String username;

    @Override
    public String getBotToken() {
       return token; 
    }

    @Override
    public void onUpdateReceived(Update update) {
        
        if(update.hasMessage() && update.getMessage().hasText()){
           String message =  update.getMessage().getText().trim();
           String chatId =  update.getMessage().getChatId().toString();
           
         //  System.out.println(token + " " + username);
         //  System.out.println(message + " " + chatId);
           
           SendMessage sm = new SendMessage(); 
           sm.setText(message);
           sm.setChatId(chatId);
           
           
            try {
                execute(sm);
            } catch (TelegramApiException ex) {
              //  Logger.getLogger(JavarushTelegramBot.class.getName()).log(Level.SEVERE, null, ex);
              ex.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return username; 
    }
    
}
