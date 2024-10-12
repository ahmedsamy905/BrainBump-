package com.project.BrainBump.repo;

//import com.project.leetcodeReminder.models.Member;
import com.project.BrainBump.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepo extends JpaRepository<Member,Long> {
}
