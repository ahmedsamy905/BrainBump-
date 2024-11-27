package com.project.BrainBump.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    private String memberName;
    @Email
    private String email;
    private String password;
    private boolean enabled;
    @OneToOne(mappedBy = "member",cascade = CascadeType.ALL,orphanRemoval = true)
    private UserProfile userProfile;
}
