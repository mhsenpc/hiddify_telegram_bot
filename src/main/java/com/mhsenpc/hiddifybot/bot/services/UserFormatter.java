package com.mhsenpc.hiddifybot.bot.services;

import com.mhsenpc.hiddifybot.bot.entity.User;
import com.mhsenpc.hiddifybot.bot.enums.UserStatus;
import org.springframework.stereotype.Service;

@Service
public class UserFormatter {

    public String getFormattedUser(User user){

        String pattern = "%s %s(%s %s) %s";

        String userStatusIcon = "";
        if(user.getStatus() == UserStatus.ACTIVE.getValue()){
            userStatusIcon = "✅";
        } else if (user.getStatus() == UserStatus.INACTIVE.getValue()) {
            userStatusIcon = "🚧";
        }

        String userRoleIcon = "";
        if(user.isAdmin()){
            userRoleIcon = "🫅";
        }

        return String.format(pattern, userStatusIcon, user.getUsername(), user.getFirstName(), user.getLastName(), userRoleIcon);
    }
}
