package com.mhsenpc.hiddifybot.bot.services;

import com.mhsenpc.hiddifybot.bot.entity.Order;
import com.mhsenpc.hiddifybot.bot.entity.Plan;
import com.mhsenpc.hiddifybot.bot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderFormatter {

    @Autowired
    private NumberFormatter numberFormatter;

    public String getFormattedOrder(Order order){

        String text = "";
        text += "%s ماهه" + System.lineSeparator();
        text += "%s گیگ" + System.lineSeparator();
        text += "کاربره %s" + System.lineSeparator();
        text += "خریدار: %s" + System.lineSeparator();

        Plan plan = order.getPlan();
        User user = order.getUser();
        String buyerText = user.getUsername() + "(" + user.getFirstName() + " " + user.getLastName() +  ")";

        return String.format(text, plan.getMonths(), plan.getTrafficLimit(), plan.getConnectionLimit(), buyerText);
    }
}
