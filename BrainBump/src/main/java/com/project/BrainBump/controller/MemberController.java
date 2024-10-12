package com.project.BrainBump.controller;



import com.project.BrainBump.models.Member;
import com.project.BrainBump.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/members")
    public List<Member>getAllmembers(){
        return memberService.getAllMembers();
    }
    @PostMapping("/register")
    public String CreateMember (@RequestBody Member member){
        memberService.saveMember(member);
        return "member added";
    }
}
