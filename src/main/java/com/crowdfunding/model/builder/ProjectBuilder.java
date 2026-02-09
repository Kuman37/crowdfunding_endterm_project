package com.crowdfunding.model.builder;

import com.crowdfunding.model.Project;
import java.math.BigDecimal;

public class ProjectBuilder {
    private int id;
    private String title;
    private int creatorId;
    private int categoryId;
    private BigDecimal goalAmount;
    private String status = "active";

    public ProjectBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public ProjectBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public ProjectBuilder setCreatorId(int creatorId) {
        this.creatorId = creatorId;
        return this;
    }

    public ProjectBuilder setCategoryId(int categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public ProjectBuilder setGoalAmount(BigDecimal goalAmount) {
        this.goalAmount = goalAmount;
        return this;
    }

    public ProjectBuilder setStatus(String status) {
        this.status = status;
        return this;
    }

    public Project build() {
        return new Project(id, title, creatorId, categoryId, goalAmount, status);
    }
}