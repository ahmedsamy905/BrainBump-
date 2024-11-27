package com.project.BrainBump.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name="confirmations")
public class Confirmation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;

    @OneToOne(targetEntity = Member.class,fetch = FetchType.EAGER)
    @JoinColumn(nullable = false,name ="member_id")
    private Member member;
    public Confirmation(Member member){
        this.member = member;
        this.createdDate= LocalDateTime.now();
        this.token= UUID.randomUUID().toString();
    }
}

