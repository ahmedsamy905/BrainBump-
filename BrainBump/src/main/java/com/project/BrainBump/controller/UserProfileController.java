package com.project.BrainBump.controller;

import com.project.BrainBump.models.UserProfile;
import com.project.BrainBump.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserProfileController {


    @Autowired
    private UserProfileService userProfileService;


    @GetMapping("/{memberId}/profile")
    public ResponseEntity<UserProfile> getProfile(@PathVariable("memberId")Long memberId){
        return new ResponseEntity<>(userProfileService.getUserProfile(memberId), HttpStatus.FOUND);
    }
}
