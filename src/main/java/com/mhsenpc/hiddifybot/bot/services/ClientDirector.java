package com.mhsenpc.hiddifybot.bot.services;

import com.mhsenpc.hiddifybot.bot.config.ConfigurationManager;
import com.mhsenpc.hiddifybot.bot.entity.Order;
import com.mhsenpc.hiddifybot.bot.entity.Plan;
import com.mhsenpc.hiddifybot.bot.enums.ConfigName;
import com.mhsenpc.hiddifybot.bot.services.name.ClientNameProvider;
import com.mhsenpc.hiddifybot.hiddify.dto.CreateUserRequestDTO;
import com.mhsenpc.hiddifybot.hiddify.dto.CreateUserResponseDTO;
import com.mhsenpc.hiddifybot.hiddify.enums.UserMode;
import com.mhsenpc.hiddifybot.hiddify.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientDirector {

    @Autowired
    private ClientNameProvider clientNameProvider;

    @Autowired
    private ConfigurationManager configurationManager;

    @Autowired
    private UserService userService;


    public CreateUserResponseDTO build(Order order) {

        String baseUrl = configurationManager.getConfig(ConfigName.BASE_URL);
        String apiKey =  configurationManager.getConfig(ConfigName.API_KEY);
        Plan plan = order.getPlan();

        CreateUserRequestDTO createUserRequestDTO = new CreateUserRequestDTO();
        createUserRequestDTO.setName(clientNameProvider.getName());
        createUserRequestDTO.setUsage_limit_GB(plan.getTrafficLimit());
        createUserRequestDTO.setPackage_days(plan.getMonths() * 30);
        createUserRequestDTO.setEnable(true);
        createUserRequestDTO.setIs_active(true);
        createUserRequestDTO.setMode(UserMode.no_reset.toString());

        return userService.createUser(baseUrl, apiKey, createUserRequestDTO);
    }
}
