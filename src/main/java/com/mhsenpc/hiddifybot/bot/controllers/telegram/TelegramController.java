package com.mhsenpc.hiddifybot.bot.controllers.telegram;

import com.mhsenpc.hiddifybot.bot.dto.UserStepWithPayload;
import com.mhsenpc.hiddifybot.bot.services.MessageService;
import com.mhsenpc.hiddifybot.telegram.services.RequestHandler;
import com.mhsenpc.hiddifybot.telegram.types.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class TelegramController implements ITelegramController {

    protected String chatId;
    protected String message;
    protected UserStepWithPayload currentStepWithPayload;

    @Autowired
    protected RequestHandler requestHandler;

    @Override
    public abstract void invoke(Update update) throws Exception;

    @Autowired
    private MessageService messageService;

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserStepWithPayload getCurrentStepWithPayload() {
        return currentStepWithPayload;
    }

    public void setCurrentStepWithPayload(UserStepWithPayload currentStepWithPayload) {
        this.currentStepWithPayload = currentStepWithPayload;
    }

    public void sendMessage(String text){

        this.messageService.send(chatId, text);
    }
}
