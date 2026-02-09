package com.crowdfunding.dto;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class RewardDTO {
    private Integer id;

    @NotBlank(message = "Title is required")
    @Size(min = 2, max = 150, message = "Title must be between 2 and 150 characters")
    private String title;

    @NotNull(message = "Project ID is required")
    private Integer projectId;

    @NotNull(message = "Minimum amount is required")
    @DecimalMin(value = "0.01", message = "Minimum amount must be greater than 0")
    private BigDecimal minAmount;

    private String description;

    public RewardDTO() {}

    public RewardDTO(String title, Integer projectId, BigDecimal minAmount) {
        this.title = title;
        this.projectId = projectId;
        this.minAmount = minAmount;
    }

    public Integer getId() { return id; }
    public String getTitle() { return title; }
    public Integer getProjectId() { return projectId; }
    public BigDecimal getMinAmount() { return minAmount; }
    public String getDescription() { return description; }

    public void setId(Integer id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setProjectId(Integer projectId) { this.projectId = projectId; }
    public void setMinAmount(BigDecimal minAmount) { this.minAmount = minAmount; }
    public void setDescription(String description) { this.description = description; }
}