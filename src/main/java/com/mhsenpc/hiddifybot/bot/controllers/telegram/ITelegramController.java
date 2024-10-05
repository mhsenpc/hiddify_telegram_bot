package com.mhsenpc.hiddifybot.bot.controllers.telegram;

import com.mhsenpc.hiddifybot.telegram.types.Update;

public interface ITelegramController {

    void invoke(Update update) throws Exception;
}
