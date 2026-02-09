package com.crowdfunding.controller;

import com.crowdfunding.dto.ApiResponse;
import com.crowdfunding.dto.ProjectDTO;
import com.crowdfunding.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProjectDTO>> createProject(@Valid @RequestBody ProjectDTO projectDTO) {
        ProjectDTO createdProject = projectService.createProject(projectDTO);
        return new ResponseEntity<>(ApiResponse.success("Project created", createdProject), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProjectDTO>> getProjectById(@PathVariable int id) {
        ProjectDTO project = projectService.getProjectById(id);
        return ResponseEntity.ok(ApiResponse.success(project));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProjectDTO>>> getAllProjects() {
        List<ProjectDTO> projects = projectService.getAllProjects();
        return ResponseEntity.ok(ApiResponse.success(projects));
    }

    @GetMapping("/creator/{creatorId}")
    public ResponseEntity<ApiResponse<List<ProjectDTO>>> getProjectsByCreator(@PathVariable int creatorId) {
        List<ProjectDTO> projects = projectService.getProjectsByCreator(creatorId);
        return ResponseEntity.ok(ApiResponse.success(projects));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<ProjectDTO>>> getProjectsByStatus(@PathVariable String status) {
        List<ProjectDTO> projects = projectService.getProjectsByStatus(status);
        return ResponseEntity.ok(ApiResponse.success(projects));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProjectDTO>> updateProject(@PathVariable int id, @Valid @RequestBody ProjectDTO projectDTO) {
        ProjectDTO updatedProject = projectService.updateProject(id, projectDTO);
        return ResponseEntity.ok(ApiResponse.success("Project updated", updatedProject));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProject(@PathVariable int id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok(ApiResponse.success("Project deleted", null));
    }
}