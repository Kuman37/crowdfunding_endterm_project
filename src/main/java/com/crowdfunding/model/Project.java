package com.crowdfunding.model;

import java.math.BigDecimal;

public class Project extends BaseEntity {
    private int creatorId;
    private int categoryId;
    private BigDecimal goalAmount;
    private String status;

    public Project(int id, String title, int creatorId, int categoryId, BigDecimal goalAmount, String status) {
        super(id, title);
        this.creatorId = creatorId;
        this.categoryId = categoryId;
        this.goalAmount = goalAmount;
        this.status = status;
    }

    @Override
    public String getSummary() {
        return "Project: " + getName() + " (" + status + ")";
    }

    public int getCreatorId() { return creatorId; }
    public int getCategoryId() { return categoryId; }
    public BigDecimal getGoalAmount() { return goalAmount; }
    public String getStatus() { return status; }

    public void setCreatorId(int creatorId) { this.creatorId = creatorId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
    public void setGoalAmount(BigDecimal goalAmount) { this.goalAmount = goalAmount; }
    public void setStatus(String status) { this.status = status; }
}