package com.mhsenpc.hiddifybot.bot.pages;

import com.mhsenpc.hiddifybot.telegram.methods.SendMessageMethod;

public class UserHomePage extends SendMessageMethod {

    public static final String BTN_MY_CONFIGS = "کانفیگ های من";

    public UserHomePage() {

        setText("کاربر گرامی. به ربات خرید وی پی ان خوش آمدید");
    }
}