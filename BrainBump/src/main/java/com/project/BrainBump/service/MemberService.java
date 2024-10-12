package com.project.BrainBump.service;




import com.project.BrainBump.models.Member;

import java.util.List;

public interface MemberService {
     void saveMember(Member Member) ;

     List<Member> getAllMembers();
}
