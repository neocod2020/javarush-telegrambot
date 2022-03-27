package com.github.javarushcommunity.jrtb.bot;

import com.github.javarushcommunity.jrtb.command.CommandContainer;
import static com.github.javarushcommunity.jrtb.command.CommandName.NO;
import com.github.javarushcommunity.jrtb.service.SendBotMessageServiceImpl;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class JavarushTelegramBot extends TelegramLongPollingBot {
    
    public static String COMMAND_PREFIX = "/";
    
    @Value("${bot.token}")
    private String token;
    
    @Value("${bot.username}")
    private String username;
    
    private final CommandContainer commandContainer;

    @Override
    public String getBotToken() {
       return token; 
    }
    @Autowired
    public JavarushTelegramBot() {
        this.commandContainer = new CommandContainer(new SendBotMessageServiceImpl(this));
    }    

    @Override
    public void onUpdateReceived(Update update) {
        
        if(update.hasMessage() && update.getMessage().hasText()){
           String message =  update.getMessage().getText().trim();
         //  String chatId =  update.getMessage().getChatId().toString();            
         //  SendMessage sm = new SendMessage(); 
         //  sm.setText(message);
         //  sm.setChatId(chatId);             
         //   try {
           //     execute(sm);
         //   } catch (TelegramApiException ex) {
              //  Logger.getLogger(JavarushTelegramBot.class.getName()).log(Level.SEVERE, null, ex);
           //   ex.printStackTrace();
            // }
            
            if (message.startsWith(COMMAND_PREFIX)){
                String commandIdenifier = message.split(" ")[0].toLowerCase();
                commandContainer.retrieveCommand(commandIdenifier).execute(update);
            } else
                commandContainer.retrieveCommand(NO.getCommandName()).execute(update);
        }
    }

    @Override
    public String getBotUsername() {
        return username; 
    }
    
}
