package com.mhsenpc.hiddifybot.bot.dto;

import com.mhsenpc.hiddifybot.bot.enums.UserCommandType;

public class UserItemButtonCallback {

    private int userId;
    private UserCommandType commandType;

    public UserItemButtonCallback() {
    }

    public UserItemButtonCallback(int userId, UserCommandType commandType) {
        this.userId = userId;
        this.commandType = commandType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public UserCommandType getCommandType() {
        return commandType;
    }
    public void setCommandType(UserCommandType commandType) {
        this.commandType = commandType;
    }

}
