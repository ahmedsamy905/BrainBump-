package com.project.BrainBump.service;

import com.project.BrainBump.models.Problem;
import com.project.BrainBump.models.ProblemAttempt;

import java.util.List;

public interface ProblemService {
    List<Problem> getAllProblems(Long memberId);

    Problem addProblem(Long memberId, Problem problem);

    String deleteProblem(Long memberId, Long problemId);

    Problem getProblemById(Long problemId);

    Problem updateProblem(Long problemId, Problem problem);

    List<Problem> getReminders(Long memberId);

    ProblemAttempt addAttempt(Long problemId, ProblemAttempt problemAttempt);
}
