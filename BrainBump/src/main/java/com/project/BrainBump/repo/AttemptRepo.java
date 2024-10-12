package com.project.BrainBump.repo;

//import com.project.leetcodeReminder.models.ProblemAttempt;
import com.project.BrainBump.models.ProblemAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttemptRepo extends JpaRepository<ProblemAttempt,Long> {
}
