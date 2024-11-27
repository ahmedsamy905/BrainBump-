package com.project.BrainBump.repo;

import com.project.BrainBump.models.ProblemAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttemptRepo extends JpaRepository<ProblemAttempt,Long> {
}
