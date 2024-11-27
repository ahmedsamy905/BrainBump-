package com.project.BrainBump.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProblemAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attemptId;

    private LocalDate attemptDate;

    @Enumerated(EnumType.STRING)
    private SolveStatus solveStatus;
    @Min(0)
    private int timeTaken;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name= "problem_id")
    private Problem problem;
}
