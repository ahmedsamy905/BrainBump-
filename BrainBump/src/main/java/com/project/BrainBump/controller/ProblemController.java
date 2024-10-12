package com.project.BrainBump.controller;


import com.project.BrainBump.models.Problem;
import com.project.BrainBump.models.ProblemAttempt;
import com.project.BrainBump.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProblemController {
    @Autowired
    private ProblemService problemService;

    // save or add problem
    @PostMapping("/{memberId}/problems")
    public Problem addProblem(@PathVariable ("memberId")Long memberId, @RequestBody Problem problem){
        return problemService.addProblem(memberId,problem);
    }


    // get all problems
    @GetMapping("/{memberId}/problems")
    public List<Problem> getAllProblem(@PathVariable("memberId") Long memberId ){
        return problemService.getAllProblems(memberId);
    }


    //delete problems
    @DeleteMapping("/{memberId}/problems/{problemId}")
    public String deleteProblem (@PathVariable("memberId") Long memberId,
                                  @PathVariable("problemId") Long problemId){
        return problemService.deleteProblem(memberId,problemId);
    }

    //get problem by id
    @GetMapping ("/problems/{problemId}")
    public Problem getProblemById (@PathVariable("problemId") Long problemId){
        return problemService.getProblemById(problemId);
    }

    //update problem
    @PutMapping("/problems/{problemId}")
    public Problem updateProblem(@PathVariable("problemId") Long problemId,
                                 @RequestBody Problem problem){
        return problemService.updateProblem(problemId,problem);
    }
    @GetMapping("/{memberId}/reminder")
    public List<Problem>getReminders(@PathVariable("memberId")Long memberId){
        return problemService.getReminders(memberId);
    }

    @PostMapping("/{problemId}/attempt")
    public ProblemAttempt addAttempt (@PathVariable("problemId")Long problemId, @RequestBody ProblemAttempt problemAttempt){
        return problemService.addAttempt(problemId,problemAttempt);
    }

}
