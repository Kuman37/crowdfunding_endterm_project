package com.crowdfunding.controller;

import com.crowdfunding.model.*;
import com.crowdfunding.service.ProjectService;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectService service;
    public ProjectController(ProjectService service) { this.service = service; }

    @PostMapping
    public Project create(@RequestBody Map<String, Object> body) {
        return service.createProject(
                (String) body.get("title"),
                (String) body.get("description"),
                new BigDecimal(body.get("goalAmount").toString()),
                (Integer) body.get("creatorId"),
                (Integer) body.get("categoryId")
        );
    }

    @GetMapping
    public List<Project> getAll() { return service.getAllProjects(); }

    @GetMapping("/{id}")
    public Project get(@PathVariable Integer id) { return service.getProject(id); }

    @PostMapping("/{id}/rewards")
    public Reward addReward(@PathVariable Integer id, @RequestBody Reward r) {
        return service.addReward(id, r);
    }

    @PostMapping("/{id}/pledge")
    public Pledge pledge(@PathVariable Integer id, @RequestBody Map<String, Object> body) {
        return service.backProject(
                id,
                (Integer) body.get("backerId"),
                (Integer) body.get("rewardId"),
                new BigDecimal(body.get("amount").toString())
        );
    }

    @PostMapping("/{id}/updates")
    public ProjectUpdate addUpdate(@PathVariable Integer id, @RequestBody Map<String, String> body) {
        return service.addUpdate(id, body.get("content"));
    }

    @PostMapping("/{id}/comments")
    public Comment addComment(@PathVariable Integer id, @RequestBody Map<String, Object> body) {
        return service.addComment(id, (Integer) body.get("userId"), (String) body.get("text"));
    }
}