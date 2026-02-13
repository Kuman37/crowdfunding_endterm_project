package com.crowdfunding.service;

import com.crowdfunding.model.*;
import com.crowdfunding.repository.*;
import com.crowdfunding.model.factory.ProjectFactory;
import com.crowdfunding.model.builder.PledgeBuilder;
import com.crowdfunding.exception.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepo;
    private final UserRepository userRepo;
    private final CategoryRepository catRepo;
    private final RewardRepository rewardRepo;
    private final PledgeRepository pledgeRepo;
    private final PaymentRepository paymentRepo;
    private final ProjectUpdateRepository updateRepo;
    private final CommentRepository commentRepo;
    private final ProjectFactory projectFactory;

    public ProjectService(ProjectRepository projectRepo, UserRepository userRepo,
                          CategoryRepository catRepo, RewardRepository rewardRepo,
                          PledgeRepository pledgeRepo, PaymentRepository paymentRepo,
                          ProjectUpdateRepository updateRepo, CommentRepository commentRepo,
                          ProjectFactory projectFactory) {
        this.projectRepo = projectRepo;
        this.userRepo = userRepo;
        this.catRepo = catRepo;
        this.rewardRepo = rewardRepo;
        this.pledgeRepo = pledgeRepo;
        this.paymentRepo = paymentRepo;
        this.updateRepo = updateRepo;
        this.commentRepo = commentRepo;
        this.projectFactory = projectFactory;
    }

    public Project createProject(String title, String desc, BigDecimal goal, Integer creatorId, Integer catId) {
        User creator = userRepo.findById(creatorId).orElseThrow(() -> new ResourceNotFoundException("Creator not found"));
        Category cat = catRepo.findById(catId).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        return projectRepo.save(projectFactory.createProject(title, desc, goal, creator, cat));
    }

    public Project getProject(Integer id) {
        return projectRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project not found"));
    }

    public List<Project> getAllProjects() { return projectRepo.findAll(); }

    public Reward addReward(Integer projId, Reward r) {
        Project p = getProject(projId);
        r.setProject(p);
        return rewardRepo.save(r);
    }

    @Transactional
    public Pledge backProject(Integer projId, Integer userId, Integer rewardId, BigDecimal amount) {
        Project p = getProject(projId);
        User u = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Reward r = null;
        if(rewardId != null) {
            r = rewardRepo.findById(rewardId).orElseThrow(() -> new ResourceNotFoundException("Reward not found"));
            if(amount.compareTo(r.getMinimumAmount()) < 0) throw new ValidationException("Amount too low for reward");
        }
        Pledge pledge = new PledgeBuilder().setProject(p).setBacker(u).setReward(r).setAmount(amount).build();
        return pledgeRepo.save(pledge);
    }

    public Payment processPayment(Integer pledgeId, String method) {
        Pledge p = pledgeRepo.findById(pledgeId).orElseThrow(() -> new ResourceNotFoundException("Pledge not found"));
        Payment pay = new Payment();
        pay.setPledge(p);
        pay.setPaymentMethod(method);
        pay.setPaymentStatus("COMPLETED");
        return paymentRepo.save(pay);
    }

    public ProjectUpdate addUpdate(Integer projId, String content) {
        Project p = getProject(projId);
        ProjectUpdate pu = new ProjectUpdate();
        pu.setProject(p);
        pu.setUpdateContent(content);
        return updateRepo.save(pu);
    }

    public Comment addComment(Integer projId, Integer userId, String text) {
        Project p = getProject(projId);
        User u = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Comment c = new Comment();
        c.setProject(p);
        c.setUser(u);
        c.setCommentText(text);
        return commentRepo.save(c);
    }
}