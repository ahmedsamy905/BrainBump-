package com.project.BrainBump.service.impl;

import com.project.BrainBump.exceptions.ResourceNotFoundException;
import com.project.BrainBump.models.Member;
import com.project.BrainBump.models.UserProfile;
import com.project.BrainBump.repo.MemberRepo;
import com.project.BrainBump.repo.UserProfileRepo;
import com.project.BrainBump.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    @Autowired
    private UserProfileRepo userProfileRepo;

    @Autowired
    private MemberRepo memberRepo;

    @Override
    public UserProfile getUserProfile(Long memberId) {
        Member member = memberRepo.findById(memberId).orElseThrow(()->new ResourceNotFoundException("member","memberId",memberId));
        return member.getUserProfile();
    }

    @Override
    public List<UserProfile> getAllUsersProfile() {
        return new ArrayList<>(userProfileRepo.findAll());
    }

    @Override
    public UserProfile createNewProfile(Member member) {
        UserProfile userProfile = new UserProfile(member.getMemberName());
        userProfile.setMember(member);
        return userProfileRepo.save(userProfile);
    }
}
