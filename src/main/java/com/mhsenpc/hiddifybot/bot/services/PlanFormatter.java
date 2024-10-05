package com.mhsenpc.hiddifybot.bot.services;

import com.mhsenpc.hiddifybot.bot.entity.Plan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanFormatter {

    @Autowired
    private NumberFormatter numberFormatter;

    public String format(Plan plan){

        return  plan.getMonths() + " ماهه" + "\n" +
                plan.getTrafficLimit() + " گیگ" + "\n" +
                plan.getConnectionLimit() + " کاربره" + "\n" ;

    }
}
