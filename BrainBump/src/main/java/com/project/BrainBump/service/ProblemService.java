package com.project.BrainBump.service;

import com.project.BrainBump.models.Problem;
import com.project.BrainBump.models.ProblemAttempt;

import java.util.List;

public interface ProblemService {
    List<Problem> getAllProblems(Long memberId);

    Problem addProblem(Long memberId, Problem problem);

    Problem deleteProblem(Long memberId, Long problemId);

    Problem getProblemById(Long problemId);

    Problem updateProblem(Long problemId, Problem problem);

    List<Problem> getReminders(Long userProfileId);

    ProblemAttempt addAttempt(Long problemId, ProblemAttempt problemAttempt);

    List<Problem> getProblemByTag(Long memberId, String tag);
}
