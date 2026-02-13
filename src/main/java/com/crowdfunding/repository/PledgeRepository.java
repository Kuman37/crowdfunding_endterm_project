package com.crowdfunding.repository;
import com.crowdfunding.model.Pledge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PledgeRepository extends JpaRepository<Pledge, Integer> {}