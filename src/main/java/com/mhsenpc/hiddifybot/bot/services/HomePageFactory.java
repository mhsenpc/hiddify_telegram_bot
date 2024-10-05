package com.mhsenpc.hiddifybot.bot.services;

import com.mhsenpc.hiddifybot.bot.pages.UserHomePage;
import com.mhsenpc.hiddifybot.bot.pages.admin.AdminHomePage;
import com.mhsenpc.hiddifybot.bot.repository.UserRepository;
import com.mhsenpc.hiddifybot.telegram.methods.SendMessageMethod;
import com.mhsenpc.hiddifybot.telegram.types.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HomePageFactory {

    @Autowired
    private UserRepository userRepository;

    public SendMessageMethod getHomePage(User telegramUser){

        if(telegramUser != null){
            com.mhsenpc.hiddifybot.bot.entity.User dbUser = userRepository.findByChatId(telegramUser.getId());
            if(dbUser == null){
                throw new RuntimeException("Couldn't find user in db");
            }

            if(dbUser.isAdmin()){
                return new AdminHomePage();
            } else if (dbUser.isNormal()) {
                return new UserHomePage();
            }
            throw new RuntimeException("Role type is not valid: " + dbUser.getRole());
        }
        throw new RuntimeException("Telegram user not provided");
    }
}
