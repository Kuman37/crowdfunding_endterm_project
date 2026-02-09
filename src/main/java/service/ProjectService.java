package com.crowdfunding.service;

import com.crowdfunding.model.Project;
import com.crowdfunding.dto.ProjectDTO;
import com.crowdfunding.repository.ProjectRepository;
import com.crowdfunding.repository.UserRepository;
import com.crowdfunding.exception.*;
import com.crowdfunding.config.LoggingService;
import com.crowdfunding.model.builder.ProjectBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    private final LoggingService logger = LoggingService.getInstance();

    @Transactional
    public ProjectDTO createProject(ProjectDTO projectDTO) {
        logger.log("Creating new project: " + projectDTO.getTitle());

        userRepository.findById(projectDTO.getCreatorId())
                .orElseThrow(() -> new ResourceNotFoundException("Creator not found"));

        if (projectDTO.getGoalAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidInputException("Goal amount must be greater than 0");
        }

        String status = projectDTO.getStatus();
        if (status == null) {
            status = "active";
        }
        if (!isValidStatus(status)) {
            throw new InvalidInputException("Invalid status. Must be: draft, active, funded, or failed");
        }

        Project project = new ProjectBuilder()
                .setTitle(projectDTO.getTitle())
                .setCreatorId(projectDTO.getCreatorId())
                .setCategoryId(projectDTO.getCategoryId())
                .setGoalAmount(projectDTO.getGoalAmount())
                .setStatus(status)
                .build();

        Project savedProject = projectRepository.save(project);

        return convertToDTO(savedProject);
    }

    public ProjectDTO getProjectById(int id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with ID: " + id));

        return convertToDTO(project);
    }

    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ProjectDTO> getProjectsByCreator(int creatorId) {
        return projectRepository.findByCreatorId(creatorId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ProjectDTO> getProjectsByStatus(String status) {
        if (!isValidStatus(status)) {
            throw new InvalidInputException("Invalid status. Must be: draft, active, funded, or failed");
        }

        return projectRepository.findByStatus(status).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProjectDTO updateProject(int id, ProjectDTO projectDTO) {
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found with ID: " + id));

        if (projectDTO.getTitle() != null) {
            existingProject.setName(projectDTO.getTitle());
        }

        if (projectDTO.getStatus() != null) {
            if (!isValidStatus(projectDTO.getStatus())) {
                throw new InvalidInputException("Invalid status. Must be: draft, active, funded, or failed");
            }
            existingProject.setStatus(projectDTO.getStatus());
        }

        projectRepository.update(existingProject);
        logger.log("Project updated with ID: " + id);

        return convertToDTO(existingProject);
    }

    @Transactional
    public void deleteProject(int id) {
        if (!projectRepository.findById(id).isPresent()) {
            throw new ResourceNotFoundException("Project not found with ID: " + id);
        }

        boolean deleted = projectRepository.delete(id);
        if (!deleted) {
            throw new RuntimeException("Failed to delete project");
        }
        logger.log("Project deleted with ID: " + id);
    }

    private ProjectDTO convertToDTO(Project project) {
        ProjectDTO dto = new ProjectDTO();
        dto.setId(project.getId());
        dto.setTitle(project.getName());
        dto.setCreatorId(project.getCreatorId());
        dto.setCategoryId(project.getCategoryId());
        dto.setGoalAmount(project.getGoalAmount());
        dto.setStatus(project.getStatus());
        return dto;
    }

    private boolean isValidStatus(String status) {
        return status != null && (status.equals("draft") || status.equals("active") ||
                status.equals("funded") || status.equals("failed"));
    }
}