package com.project.BrainBump.controller;


import com.project.BrainBump.models.Problem;
import com.project.BrainBump.models.ProblemAttempt;
import com.project.BrainBump.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProblemController {
    @Autowired
    private ProblemService problemService;

    // save or add problem
    @PostMapping("/{memberId}/problems")
    public ResponseEntity<Problem> addProblem(@PathVariable ("memberId")Long memberId, @RequestBody Problem problem){
        return new ResponseEntity<>(problemService.addProblem(memberId,problem), HttpStatus.CREATED);
    }


    // get all problems
    @GetMapping("/{memberId}/problems")
    public ResponseEntity<List<Problem>> getAllProblem(@PathVariable("memberId") Long memberId ){
        return  new ResponseEntity<>(problemService.getAllProblems(memberId), HttpStatus.OK);
    }


    //delete problems
    @DeleteMapping("/{memberId}/problems/{problemId}")
    public ResponseEntity<Problem> deleteProblem (@PathVariable("memberId") Long memberId,
                                  @PathVariable("problemId") Long problemId){
        return new ResponseEntity<>(problemService.deleteProblem(memberId,problemId), HttpStatus.OK);
    }

    //get problem by id
    @GetMapping ("/problems/{problemId}")
    public ResponseEntity<Problem> getProblemById (@PathVariable("problemId") Long problemId){
        return new ResponseEntity<>(problemService.getProblemById(problemId), HttpStatus.FOUND);
    }

    //update problem
    @PutMapping("/problems/{problemId}")
    public ResponseEntity<Problem> updateProblem(@PathVariable("problemId") Long problemId,
                                 @RequestBody Problem problem){
        return new ResponseEntity<>(problemService.updateProblem(problemId,problem), HttpStatus.OK);
    }

    //get list to solve today
    @GetMapping("/{userProfileId}/reminder")
    public ResponseEntity<List<Problem>>getReminders(@PathVariable("userProfileId")Long userProfileId){
        return new ResponseEntity<>(problemService.getReminders(userProfileId), HttpStatus.OK);
    }

    //add attempt to solve problem
    @PostMapping("/{problemId}/attempt")
    public ResponseEntity<ProblemAttempt> addAttempt (@PathVariable("problemId")Long problemId, @RequestBody ProblemAttempt problemAttempt){
        return new ResponseEntity<>(problemService.addAttempt(problemId,problemAttempt), HttpStatus.CREATED);
    }

    //get problems by tag
    @GetMapping("/{memberId}/problems/{tag}")
    public ResponseEntity <List<Problem>> getProblemByTag(@PathVariable("memberId")Long memberId, @PathVariable("tag")String tag){
        return new ResponseEntity<>(problemService.getProblemByTag(memberId,tag), HttpStatus.FOUND);
    }

}
