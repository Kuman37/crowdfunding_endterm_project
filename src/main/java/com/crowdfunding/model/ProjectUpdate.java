package com.crowdfunding.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "project_update")
public class ProjectUpdate extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(name = "update_content", nullable = false, columnDefinition = "TEXT")
    private String updateContent;

    @Column(name = "update_date")
    private LocalDateTime updateDate = LocalDateTime.now();

    public ProjectUpdate() {}

    public Project getProject() { return project; }
    public void setProject(Project project) { this.project = project; }
    public String getUpdateContent() { return updateContent; }
    public void setUpdateContent(String updateContent) { this.updateContent = updateContent; }
    public LocalDateTime getUpdateDate() { return updateDate; }
}