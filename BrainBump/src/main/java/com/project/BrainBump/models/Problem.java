package com.project.BrainBump.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.LocalDate;

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
    @Min(1)
    private int currentInterval;

    @Enumerated(EnumType.STRING)
    private SolveStatus status;

    private Long numOfSolving;
    private LocalDate theLastAttempt;
    private LocalDate theNextReminder;
    @Min(0)
    private int timeTaken;
    @Getter
    @Setter
    private String tag;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userProfile_id")
    private UserProfile userProfile;

    @OneToMany(mappedBy = "problem",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ProblemAttempt> problemAttempts ;
}
