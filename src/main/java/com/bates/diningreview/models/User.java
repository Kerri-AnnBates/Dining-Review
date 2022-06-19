package com.bates.diningreview.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "UserAccount")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true)
    private String username;
    private String city;
    private String state;
    private String zipcode;
    private Boolean interestInPeanutAllergy;
    private Boolean interestInEggAllergy;
    private Boolean interestInDiaryAllergy;
}
