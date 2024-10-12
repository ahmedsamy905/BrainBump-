package com.project.BrainBump.service;
//
//import com.project.leetcodeReminder.exceptions.ResourceNotFoundException;
//import com.project.leetcodeReminder.models.Member;
//import com.project.leetcodeReminder.models.Problem;
//import com.project.leetcodeReminder.models.ProblemAttempt;
//import com.project.leetcodeReminder.models.SolveStatus;
//import com.project.leetcodeReminder.repos.AttemptRepo;
//import com.project.leetcodeReminder.repos.MemberRepo;
//import com.project.leetcodeReminder.repos.ProblemRepo;


import com.project.BrainBump.exceptions.ResourceNotFoundException;
import com.project.BrainBump.models.Member;
import com.project.BrainBump.models.Problem;
import com.project.BrainBump.models.ProblemAttempt;
import com.project.BrainBump.models.SolveStatus;
import com.project.BrainBump.repo.AttemptRepo;
import com.project.BrainBump.repo.MemberRepo;
import com.project.BrainBump.repo.ProblemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProblemServiceImpl implements ProblemService{


    @Autowired
    private MemberRepo memberRepo;
    @Autowired
    private ProblemRepo problemRepo;
    @Autowired
    private AttemptRepo attemptRepo;

    @Override
    public List<Problem> getAllProblems(Long memberId) {
        Member member= memberRepo.findById(memberId).orElseThrow(()->new ResourceNotFoundException("member","memberId",memberId));
        return member.getProblems();
    }

    @Override
    public Problem addProblem(Long memberId, Problem problem) {
        Member member= memberRepo.findById(memberId).orElseThrow(()->new ResourceNotFoundException("member","memberId",memberId));
        List<Problem>problems = member.getProblems();
        problem.setTheLastAttempt(LocalDate.now());
        problem.setTheNextReminder(LocalDate.now().plusDays(1));
        problem.setCurrentInterval(1);
        problem.setNumOfSolving(1L);
        problem.setStatus(SolveStatus.PASS);
        problem.setMember(member);
        Problem savedProblem = problemRepo.save(problem);
        problems.add(savedProblem);
        member.setProblems(problems);
        member.setProblemNum((long) problems.size());
        memberRepo.save(member);
        return problem;
    }

    @Override
    public String deleteProblem(Long memberId, Long problemId) {
        Member member= memberRepo.findById(memberId).orElseThrow(()->new ResourceNotFoundException("member","memberId",memberId));
        List<Problem>problems = member.getProblems();
        for(int i = 0 ;i<problems.size();i++){
            if(problems.get(i).getProblemId().equals(problemId)){
                Problem problem = problems.get(i);
                problemRepo.delete(problem);
                problems.remove(i);
                member.setProblems(problems);
                member.setProblemNum((long) problems.size());
                memberRepo.save(member);
                return "deleted";
            }
        }
        return "problem not found";
    }

    @Override
    public Problem getProblemById(Long problemId) {
        Problem problem = problemRepo.findById(problemId).orElseThrow(()->new ResourceNotFoundException("problem","problem id",problemId));
        return problem;
    }

    @Override
    public Problem updateProblem(Long problemId, Problem problem) {
        Problem problemFromDB  = problemRepo.findById(problemId).orElseThrow(()->new ResourceNotFoundException("problem","problem id",problemId));
        problemFromDB.setProblemNote(problem.getProblemNote());
        problemFromDB.setProblemUrl(problem.getProblemUrl());
        problemFromDB.setNumOfSolving(problem.getNumOfSolving());
        problemFromDB.setTheLastAttempt(problem.getTheLastAttempt());
        return problemRepo.save(problemFromDB);
    }

    @Override
    public List<Problem> getReminders(Long memberId) {
        Member member= memberRepo.findById(memberId).orElseThrow(()->new ResourceNotFoundException("member","memberId",memberId));
        List<Problem> problems = member.getProblems();
        List<Problem> reminders = new ArrayList<>();
        for(int i =0;i<problems.size();i++){
            if(problems.get(i).getTheNextReminder().equals(LocalDate.now())){
                reminders.add(problems.get(i));
            }
        }
        return reminders;
    }

    @Override
    public ProblemAttempt addAttempt(Long problemId, ProblemAttempt problemAttempt) {
        Problem problem  = problemRepo.findById(problemId).orElseThrow(()->new ResourceNotFoundException("problem","problem id",problemId));
        problemAttempt.setProblem(problem);
        attemptRepo.save(problemAttempt);
        List <ProblemAttempt>attempts = problem.getProblemAttempts();
        attempts.add(problemAttempt);
        problem.setProblemAttempts(attempts);
        if(problemAttempt.getSolveStatus().equals(SolveStatus.PASS)){
            int currentInterval = problem.getCurrentInterval();
            if(currentInterval == 48){
                problem.setTheNextReminder(LocalDate.now().plusDays(currentInterval));
            }else{
                problem.setTheNextReminder(LocalDate.now().plusDays(currentInterval));
                problem.setCurrentInterval(currentInterval*2);
            }
        }else{
            int currentInterval = problem.getCurrentInterval();
            problem.setCurrentInterval(currentInterval/2);
            problem.setTheNextReminder(LocalDate.now().plusDays(currentInterval));
        }
        problem.setNumOfSolving(problem.getNumOfSolving()+1);
        problem.setTheLastAttempt(LocalDate.now());
        problem.setTimeTaken(problemAttempt.getTimeTaken());
        problem.setStatus(problemAttempt.getSolveStatus());
        problemRepo.save(problem);
        return problemAttempt;
    }
}
