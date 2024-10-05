package com.mhsenpc.hiddifybot.bot.pages.admin.orders;

import com.mhsenpc.hiddifybot.bot.pages.BasePage;
import com.mhsenpc.hiddifybot.telegram.types.keyaboard.KeyboardButton;
import com.mhsenpc.hiddifybot.telegram.types.keyaboard.ReplyKeyboardMarkup;
import org.springframework.stereotype.Component;

@Component
public class ViewOrdersPage extends BasePage {

    public static final String BTN_PENDING_ORDERS = "سفارشات در انتظار تایید";

    public ViewOrdersPage() {

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.addRow(new KeyboardButton(BTN_PENDING_ORDERS));
        replyKeyboardMarkup.addRow(new KeyboardButton(BasePage.BTN_BACK));
        this.setReplyMarkup(replyKeyboardMarkup);
    }
}
