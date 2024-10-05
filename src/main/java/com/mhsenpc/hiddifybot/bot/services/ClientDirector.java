package com.mhsenpc.hiddifybot.bot.services;

import com.mhsenpc.hiddifybot.bot.config.ConfigurationManager;
import com.mhsenpc.hiddifybot.bot.entity.Order;
import com.mhsenpc.hiddifybot.bot.services.name.ClientNameProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientDirector {

    @Autowired
    private ClientNameProvider clientNameProvider;

    @Autowired
    private ConfigurationManager configurationManager;

    public void build(Order order) {

        // proxy between system and api
    }
}
