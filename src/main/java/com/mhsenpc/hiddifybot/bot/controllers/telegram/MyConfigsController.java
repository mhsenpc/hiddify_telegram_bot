package com.mhsenpc.hiddifybot.bot.controllers.telegram;

import com.mhsenpc.hiddifybot.bot.entity.Client;
import com.mhsenpc.hiddifybot.bot.entity.User;
import com.mhsenpc.hiddifybot.bot.repository.UserRepository;
import com.mhsenpc.hiddifybot.telegram.types.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyConfigsController extends TelegramController {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void invoke(Update update) {
        User dbUser = userRepository.findByChatId(this.chatId);
        printRealConfigs(dbUser);
    }

    private void printRealConfigs(User dbUser) {

        for(Client client: dbUser.getClients()){
            String text = client.getOrder().getPlan().getMonths() + "ماهه " + System.lineSeparator();
            text += client.getOrder().getPlan().getTrafficLimit() + "گیگ " + System.lineSeparator();
            if(client.getValidUntil().after(new Date())){
                text += "منقضی شده است" + System.lineSeparator();
            }
            else{
                text += "اعتبار زمانی دارد" + System.lineSeparator();
            }
            text += client.getUrl();
            sendMessage(text);
        }
    }
}
