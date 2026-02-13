package com.crowdfunding.model.factory;

import com.crowdfunding.model.Project;
import com.crowdfunding.model.User;
import com.crowdfunding.model.Category;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class ProjectFactory {
    public Project createProject(String title, String desc, BigDecimal goal, User creator, Category category) {
        Project p = new Project();
        p.setTitle(title);
        p.setDescription(desc);
        p.setGoalAmount(goal);
        p.setCreator(creator);
        p.setCategory(category);
        p.setStatus("draft");
        p.setStartDate(LocalDate.now());
        p.setEndDate(LocalDate.now().plusDays(30));
        return p;
    }
}