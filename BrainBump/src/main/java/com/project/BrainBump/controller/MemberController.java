package com.project.BrainBump.controller;



import com.project.BrainBump.models.Member;
import com.project.BrainBump.service.EmailService;
import com.project.BrainBump.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberController {

    @Autowired
    private EmailService emailService;
    @Autowired
    private MemberService memberService;


    //get all users
    @GetMapping("/members")
    public ResponseEntity<List<Member>> getAllmembers(){
        return new ResponseEntity<>(memberService.getAllMembers(), HttpStatus.FOUND);
    }

    //create new account
    @PostMapping("/register")
    public ResponseEntity<Member> CreateMember (@RequestBody Member member){
        memberService.saveMember(member);
        return new ResponseEntity<>(member,HttpStatus.CREATED);
    }

    @GetMapping("/register")
    public ResponseEntity<String>verifyAccount(@RequestParam("token") String token){
        String response = emailService.verifyAccount(token);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


}
