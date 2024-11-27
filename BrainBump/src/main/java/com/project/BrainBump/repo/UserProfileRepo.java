package com.project.BrainBump.repo;

import com.project.BrainBump.models.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepo extends JpaRepository<UserProfile,Long> {
}
