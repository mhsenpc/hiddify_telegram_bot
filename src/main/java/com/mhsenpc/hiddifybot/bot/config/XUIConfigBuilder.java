package com.mhsenpc.hiddifybot.bot.config;

import com.mhsenpc.hiddifybot.bot.enums.ConfigName;
import com.mhsenpc.hiddifybot.xui.dto.XuiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class XUIConfigBuilder {

    @Autowired
    private ConfigurationManager configurationManager;

    public XuiConfig build(){

        return new XuiConfig(
                configurationManager.getConfig(ConfigName.PANEL_BASE_URL),
                configurationManager.getConfig(ConfigName.PANEL_USERNAME),
                configurationManager.getConfig(ConfigName.PANEL_PASSWORD),
                configurationManager.getConfig(ConfigName.INBOUND_ID_OVERRIDE),
                configurationManager.getConfig(ConfigName.VPN_HOST),
                configurationManager.getConfig(ConfigName.VPN_PORT)
        );
    }
}
