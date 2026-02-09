package com.crowdfunding.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class ProjectDTO {
    private Integer id;

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 200, message = "Title must be between 3 and 200 characters")
    private String title;

    @NotNull(message = "Creator ID is required")
    private Integer creatorId;

    @NotNull(message = "Category ID is required")
    private Integer categoryId;

    @NotNull(message = "Goal amount is required")
    @DecimalMin(value = "0.01", message = "Goal amount must be greater than 0")
    private BigDecimal goalAmount;

    private String status = "active";

    public ProjectDTO() {}

    public Integer getId() { return id; }
    public String getTitle() { return title; }
    public Integer getCreatorId() { return creatorId; }
    public Integer getCategoryId() { return categoryId; }
    public BigDecimal getGoalAmount() { return goalAmount; }
    public String getStatus() { return status; }

    public void setId(Integer id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setCreatorId(Integer creatorId) { this.creatorId = creatorId; }
    public void setCategoryId(Integer categoryId) { this.categoryId = categoryId; }
    public void setGoalAmount(BigDecimal goalAmount) { this.goalAmount = goalAmount; }
    public void setStatus(String status) { this.status = status; }
}