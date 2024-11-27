package com.project.BrainBump.service.impl;


import com.project.BrainBump.models.Confirmation;
import com.project.BrainBump.models.Member;
import com.project.BrainBump.models.UserProfile;
import com.project.BrainBump.repo.ConfirmationRepo;
import com.project.BrainBump.repo.MemberRepo;
import com.project.BrainBump.service.EmailService;
import com.project.BrainBump.service.MemberService;
import com.project.BrainBump.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserProfileService userProfileService;
    @Autowired
    private MemberRepo memberRepo;
    @Autowired
    private ConfirmationRepo confirmationRepo;
    @Override
    public void saveMember(Member member) {
        if(memberRepo.existsByEmail(member.getEmail())){
            throw new RuntimeException("email already exists");
        }
        member.setEnabled(false);
        memberRepo.save(member);
        Confirmation confirmation = new Confirmation(member);
        confirmationRepo.save(confirmation);



        //sending simple email to verify

        //------->>>>>>>>>emailService.sendSimpleMailMessage(member.getMemberName(),member.getEmail(),confirmation.getToken());

        //sending simple email to verify
        emailService.sendHtmlEmail(member.getMemberName(),member.getEmail(),confirmation.getToken());


    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepo.findAll();
    }
}
