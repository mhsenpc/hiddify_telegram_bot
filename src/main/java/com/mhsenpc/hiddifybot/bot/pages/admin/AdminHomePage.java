package com.mhsenpc.hiddifybot.bot.pages.admin;

import com.mhsenpc.hiddifybot.telegram.methods.SendMessageMethod;
import com.mhsenpc.hiddifybot.telegram.types.keyaboard.KeyboardButton;
import com.mhsenpc.hiddifybot.telegram.types.keyaboard.ReplyKeyboardMarkup;

public class AdminHomePage extends SendMessageMethod {

    public static final String BTN_VIEW_USERS = "کاربران";
    public static final String BTN_VIEW_PLANS = "تعرفه ها";
    public static final String BTN_CREATE_CONFIG = "ساخت کانفیگ";


    public AdminHomePage() {

        setText("مدیر  گرامی. به ربات خرید وی پی ان خوش آمدید");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.addRow(new KeyboardButton(BTN_CREATE_CONFIG));
        replyKeyboardMarkup.addRow(new KeyboardButton(BTN_VIEW_USERS));
        replyKeyboardMarkup.addRow(new KeyboardButton(BTN_VIEW_PLANS));
        this.setReplyMarkup(replyKeyboardMarkup);
    }
}