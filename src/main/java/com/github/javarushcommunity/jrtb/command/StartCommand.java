package com.github.javarushcommunity.jrtb.command;

import com.github.javarushcommunity.jrtb.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;


public class StartCommand implements Command {
    
    private final SendBotMessageService sendBotMessageService;
    
    public final static String START_MESSAGE = 
            "Привет! Я JavarushTelegrambot. Я помогу вам быть в курсе статей тех авторов, "
            + "которые вам интересны. Я еще маленький и только учусь";

    // не используем сервис @Autowired, т.к.при этом возникает циклическая зависимость
    public StartCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }  
    
    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), START_MESSAGE);
    }
    
}
