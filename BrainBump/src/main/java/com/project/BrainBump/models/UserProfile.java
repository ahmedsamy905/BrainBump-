package com.project.BrainBump.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userProfileId;
    private String profileImage;
    private String profileName;
    @Min(0)
    private Long problemNum;
    @Getter
    @Setter
    @OneToMany(mappedBy = "userProfile",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Problem> problems=new ArrayList<>();
    @JsonIgnore
    @OneToOne
    @Getter
    @Setter
    @JoinColumn(name = "member_id")
    private Member member ;

    public UserProfile(String profileName) {
        this.profileName = profileName;
    }
}
