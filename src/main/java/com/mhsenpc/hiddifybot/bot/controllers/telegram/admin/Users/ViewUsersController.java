package com.mhsenpc.hiddifybot.bot.controllers.telegram.admin.Users;

import com.mhsenpc.hiddifybot.bot.controllers.telegram.TelegramController;
import com.mhsenpc.hiddifybot.bot.dto.UserStepWithPayload;
import com.mhsenpc.hiddifybot.bot.entity.User;
import com.mhsenpc.hiddifybot.bot.enums.UserRole;
import com.mhsenpc.hiddifybot.bot.enums.UserStep;
import com.mhsenpc.hiddifybot.bot.repository.UserRepository;
import com.mhsenpc.hiddifybot.bot.services.UserFormatter;
import com.mhsenpc.hiddifybot.bot.services.UserKeyboardActions;
import com.mhsenpc.hiddifybot.bot.services.UserStepService;
import com.mhsenpc.hiddifybot.telegram.methods.SendMessageMethod;
import com.mhsenpc.hiddifybot.telegram.types.Message;
import com.mhsenpc.hiddifybot.telegram.types.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ViewUsersController extends TelegramController {

    @Autowired
    private UserStepService userStepService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserKeyboardActions userKeyboardActions;

    @Autowired
    private UserFormatter userFormatter;

    @Override
    public void invoke(Update update) throws Exception {

        User currentUser = this.userRepository.findByChatId(chatId);

        if (currentUser.getRole() != UserRole.ADMIN.getValue()) {
            sendMessage("شما اجازه دسترسی به این بخش را ندارید. لطفا با مدیر سیستم تماس بگیرید");
            return;
        }

        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            this.sendMessage("هیچ کاربری برای نمایش وجود ندارد");
        }
        for (User user : users) {

            SendMessageMethod userItemMessage = new SendMessageMethod();
            userItemMessage.setChatId(chatId);
            userItemMessage.setText(userFormatter.getFormattedUser(user));
            userItemMessage.setReplyMarkup(userKeyboardActions.getKeyboardForUser(user));
            requestHandler.send(userItemMessage, Message.class);
        }

        userStepService.set(chatId, new UserStepWithPayload(UserStep.ADMIN_VIEW_USERS));
    }
}
