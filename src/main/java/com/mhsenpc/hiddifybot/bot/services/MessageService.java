package com.mhsenpc.hiddifybot.bot.services;

import com.mhsenpc.hiddifybot.telegram.methods.SendMessageMethod;
import com.mhsenpc.hiddifybot.telegram.methods.SendPhotoMethod;
import com.mhsenpc.hiddifybot.telegram.services.RequestHandler;
import com.mhsenpc.hiddifybot.telegram.types.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private RequestHandler requestHandler;

    public void send(String chatId, String message){

        SendMessageMethod sendMessageMethod = new SendMessageMethod();
        sendMessageMethod.setChatId(chatId);
        sendMessageMethod.setText(message);
        this.requestHandler.send(sendMessageMethod, Message.class);
    }

    public void sendPhoto(String chatId, String photo, String caption){

        SendPhotoMethod sendPhotoMethod = new SendPhotoMethod();
        sendPhotoMethod.setChatId(chatId);
        sendPhotoMethod.setCaption(caption);
        sendPhotoMethod.setPhoto(photo);
        this.requestHandler.send(sendPhotoMethod, Message.class);
    }
}
