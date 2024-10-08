package com.mhsenpc.hiddifybot.bot.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "plans")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int planId;
    private int months;
    private int trafficLimit;
    private Date deletedAt;

    public Plan() {
    }

    public Plan(int months, int trafficLimit) {
        this.months = months;
        this.trafficLimit = trafficLimit;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

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

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "planId=" + planId +
                ", months=" + months +
                ", trafficLimit=" + trafficLimit +
                '}';
    }
}
