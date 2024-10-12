package com.project.BrainBump.service;

//import com.project.leetcodeReminder.models.Member;
//import com.project.leetcodeReminder.repos.MemberRepo;
import com.project.BrainBump.models.Member;
import com.project.BrainBump.repo.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    private MemberRepo memberRepo;

    @Override
    public void saveMember(Member Member) {
        System.out.println("ajkdfjl;adjfl;ajsd;faj");
        memberRepo.save(Member);
    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepo.findAll();
    }
}
