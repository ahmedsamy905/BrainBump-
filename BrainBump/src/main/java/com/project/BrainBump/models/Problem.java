package com.project.BrainBump.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
//import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long problemId;
    private String problemUrl;
    private String problemNote;
//    @Min(0)
    private int currentInterval;

    @Enumerated(EnumType.STRING)
    private SolveStatus status;

    private Long numOfSolving;
    private LocalDate theLastAttempt;
    private LocalDate theNextReminder;
//    @Min(0)
    private int timeTaken;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "problem",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ProblemAttempt> problemAttempts ;
}
