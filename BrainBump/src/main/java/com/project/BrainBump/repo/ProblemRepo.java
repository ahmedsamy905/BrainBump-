package com.project.BrainBump.repo;

import com.project.BrainBump.models.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProblemRepo extends JpaRepository<Problem,Long> {
//    @Query("SELECT p FROM Problem p WHERE p.userProfile.id = :userProfileId AND p.theNextReminder = :date")
//    List<Problem> findRemindersByUserProfileIdAndDate(@Param("userProfileId") Long userProfileId, @Param("date") LocalDate date);
}
