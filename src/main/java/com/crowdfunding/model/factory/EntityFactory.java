package com.crowdfunding.model.factory;

import com.crowdfunding.model.BaseEntity;
import com.crowdfunding.model.Project;
import com.crowdfunding.model.Reward;
import com.crowdfunding.model.User;
import org.springframework.stereotype.Component;

@Component
public class EntityFactory {

    public BaseEntity createProject(int id, String title, int creatorId, int categoryId,
                                    java.math.BigDecimal goalAmount, String status) {
        return new Project(id, title, creatorId, categoryId, goalAmount, status);
    }

    public BaseEntity createReward(int id, String title, int projectId, java.math.BigDecimal minAmount) {
        return new Reward(id, title, projectId, minAmount);
    }

    public BaseEntity createUser(int id, String fullName, String email, String role) {
        return new User(id, fullName, email, role);
    }
}