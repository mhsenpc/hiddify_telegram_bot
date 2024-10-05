package com.mhsenpc.hiddifybot.bot.services;

import com.mhsenpc.hiddifybot.bot.config.ConfigurationManager;
import com.mhsenpc.hiddifybot.bot.config.XUIConfigBuilder;
import com.mhsenpc.hiddifybot.bot.entity.Order;
import com.mhsenpc.hiddifybot.bot.entity.Plan;
import com.mhsenpc.hiddifybot.bot.services.name.ClientNameProvider;
import com.mhsenpc.hiddifybot.xui.dto.XUIClient;
import com.mhsenpc.hiddifybot.xui.exceptions.InboundNotRetrievedException;
import com.mhsenpc.hiddifybot.xui.services.ClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ClientDirector {

    @Autowired
    private ClientBuilder clientBuilder;

    @Autowired
    private ClientNameProvider clientNameProvider;

    @Autowired
    private ConfigurationManager configurationManager;

    @Autowired
    private XUIConfigBuilder xuiConfigBuilder;

    public XUIClient build(Order order) throws InboundNotRetrievedException, IOException {

        clientBuilder.setXuiConfig(xuiConfigBuilder.build());
        clientBuilder.setEmail(clientNameProvider.getName());
        Plan plan = order.getPlan();
        clientBuilder.setTrafficLimitInGB(plan.getTrafficLimit());
        clientBuilder.setConnectionLimit(plan.getConnectionLimit());
        clientBuilder.setExpiryTimeInMonths(plan.getMonths());
        return clientBuilder.build();
    }
}
