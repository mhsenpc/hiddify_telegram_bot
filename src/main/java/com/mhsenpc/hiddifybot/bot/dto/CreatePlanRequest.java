package com.mhsenpc.hiddifybot.bot.dto;

public class CreatePlanRequest {

    private int months;
    private int trafficLimit;

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public int getTrafficLimit() {
        return trafficLimit;
    }

    public void setTrafficLimit(int trafficLimit) {
        this.trafficLimit = trafficLimit;
    }

    @Override
    public String toString() {
        return "CreatePlanRequest{" +
                ", months=" + months +
                ", trafficLimit=" + trafficLimit +
                '}';
    }
}
