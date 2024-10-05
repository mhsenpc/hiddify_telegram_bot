package com.mhsenpc.hiddifybot.bot.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mhsenpc.hiddifybot.bot.dto.PlanItemButtonCallback;

public class PlanItemButtonCallbackSerializer {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String serialize(PlanItemButtonCallback obj) throws Exception {
        return objectMapper.writeValueAsString(obj);
    }

    public static PlanItemButtonCallback deserialize(String json) throws Exception {
        return objectMapper.readValue(json, PlanItemButtonCallback.class);
    }
}