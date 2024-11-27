package com.project.BrainBump.service;

import com.project.BrainBump.models.Member;
import com.project.BrainBump.models.UserProfile;

import java.util.List;

public interface UserProfileService {
    UserProfile getUserProfile(Long memberId);
    List <UserProfile>getAllUsersProfile ();
    UserProfile createNewProfile(Member member);
}
