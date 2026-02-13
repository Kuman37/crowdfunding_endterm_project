package com.crowdfunding.model.builder;

import com.crowdfunding.model.Pledge;
import com.crowdfunding.model.Project;
import com.crowdfunding.model.User;
import com.crowdfunding.model.Reward;
import java.math.BigDecimal;

public class PledgeBuilder {
    private Project project;
    private User backer;
    private Reward reward;
    private BigDecimal amount;

    public PledgeBuilder setProject(Project project) {
        this.project = project;
        return this;
    }

    public PledgeBuilder setBacker(User backer) {
        this.backer = backer;
        return this;
    }

    public PledgeBuilder setReward(Reward reward) {
        this.reward = reward;
        return this;
    }

    public PledgeBuilder setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public Pledge build() {
        Pledge pledge = new Pledge();
        pledge.setProject(this.project);
        pledge.setBacker(this.backer);
        pledge.setReward(this.reward);
        pledge.setPledgeAmount(this.amount);
        return pledge;
    }
}