package com.crowdfunding.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;

@Entity
@Table(name = "reward")
public class Reward extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    @JsonIgnoreProperties("rewards")
    private Project project;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(name = "minimum_amount", nullable = false)
    private BigDecimal minimumAmount;

    public Reward() {}
    public Reward(String title, BigDecimal minimumAmount) {
        this.title = title;
        this.minimumAmount = minimumAmount;
    }

    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public BigDecimal getMinimumAmount() { return minimumAmount; }
    public void setMinimumAmount(BigDecimal minimumAmount) { this.minimumAmount = minimumAmount; }
}