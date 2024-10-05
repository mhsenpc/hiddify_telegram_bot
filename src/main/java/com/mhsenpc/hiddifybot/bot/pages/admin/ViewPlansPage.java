package com.mhsenpc.hiddifybot.bot.pages.admin;

import com.mhsenpc.hiddifybot.bot.pages.BasePage;
import com.mhsenpc.hiddifybot.telegram.types.keyaboard.KeyboardButton;
import com.mhsenpc.hiddifybot.telegram.types.keyaboard.ReplyKeyboardMarkup;
import org.springframework.stereotype.Component;

@Component
public class ViewPlansPage extends BasePage {

    public static final String BTN_ADD_PLAN = "افزودن تعرفه";

    public ViewPlansPage() {

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.addRow(new KeyboardButton(BTN_ADD_PLAN));
        replyKeyboardMarkup.addRow(new KeyboardButton(AdminHomePage.BTN_VIEW_PLANS));
        replyKeyboardMarkup.addRow(new KeyboardButton(BasePage.BTN_BACK));
        this.setReplyMarkup(replyKeyboardMarkup);
    }
}
