package com.project.BrainBump.repo;

//import com.project.leetcodeReminder.models.Problem;
import com.project.BrainBump.models.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepo extends JpaRepository<Problem,Long> {
}
