package com.project.BrainBump.repo;

import com.project.BrainBump.models.Confirmation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationRepo extends JpaRepository<Confirmation,Long> {
    Confirmation findByToken(String token);
}
