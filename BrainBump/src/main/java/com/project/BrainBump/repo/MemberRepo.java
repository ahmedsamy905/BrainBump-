package com.project.BrainBump.repo;

import com.project.BrainBump.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepo extends JpaRepository<Member,Long> {
    Member findByEmail (String email);
    boolean existsByEmail(String email);
}
