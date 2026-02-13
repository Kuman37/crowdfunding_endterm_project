package com.crowdfunding.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pledge")
public class Pledge extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne
    @JoinColumn(name = "backer_id", nullable = false)
    private User backer;

    @ManyToOne
    @JoinColumn(name = "reward_id")
    private Reward reward;

    @Column(name = "pledge_amount", nullable = false)
    private BigDecimal pledgeAmount;

    @Column(name = "pledge_date")
    private LocalDateTime pledgeDate = LocalDateTime.now();

    public Pledge() {}

    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }
    public User getBacker() { return backer; }
    public void setBacker(User backer) { this.backer = backer; }
    public Reward getReward() { return reward; }
    public void setReward(Reward reward) { this.reward = reward; }
    public BigDecimal getPledgeAmount() { return pledgeAmount; }
    public void setPledgeAmount(BigDecimal pledgeAmount) { this.pledgeAmount = pledgeAmount; }
    public LocalDateTime getPledgeDate() { return pledgeDate; }
}