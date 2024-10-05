package com.mhsenpc.hiddifybot.bot.controllers.telegram;

import com.mhsenpc.hiddifybot.bot.services.HomePageFactory;
import com.mhsenpc.hiddifybot.telegram.methods.SendMessageMethod;
import com.mhsenpc.hiddifybot.telegram.types.Message;
import com.mhsenpc.hiddifybot.telegram.types.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainMenuController extends TelegramController {

    @Autowired
    private HomePageFactory homePageFactory;

    @Override
    public void invoke(Update update) {

        SendMessageMethod homePage = homePageFactory.getHomePage(update.getMessage().getFrom());
        homePage.setChatId(chatId);

        this.requestHandler.send(homePage, Message.class);
    }
}
