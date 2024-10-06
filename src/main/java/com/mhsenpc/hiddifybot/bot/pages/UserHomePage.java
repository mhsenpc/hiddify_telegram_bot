package com.mhsenpc.hiddifybot.bot.pages;

import com.mhsenpc.hiddifybot.telegram.methods.SendMessageMethod;
import com.mhsenpc.hiddifybot.telegram.types.keyaboard.ReplyKeyboardRemove;

public class UserHomePage extends SendMessageMethod {

    public static final String BTN_MY_CONFIGS = "کانفیگ های من";

    public UserHomePage() {

        setText("برای استفاده از ربات با ادمین تماس بگیرید");
        this.setReplyMarkup(new ReplyKeyboardRemove(true));
    }
}