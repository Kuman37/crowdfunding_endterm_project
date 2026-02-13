package com.crowdfunding.repository;
import com.crowdfunding.model.ProjectUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectUpdateRepository extends JpaRepository<ProjectUpdate, Integer> {}