package com.crowdfunding.model;

import java.math.BigDecimal;

public class Reward extends BaseEntity {
    private int projectId;
    private BigDecimal minAmount;

    public Reward(int id, String title, int projectId, BigDecimal minAmount) {
        super(id, title);
        this.projectId = projectId;
        this.minAmount = minAmount;
    }

    @Override
    public String getSummary() {
        return "Reward: " + getName() + " > $" + minAmount;
    }

    public int getProjectId() { return projectId; }
    public BigDecimal getMinAmount() { return minAmount; }

    public void setProjectId(int projectId) { this.projectId = projectId; }
    public void setMinAmount(BigDecimal minAmount) { this.minAmount = minAmount; }
}