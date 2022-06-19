package com.bates.diningreview.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Restaurant")
@Getter
@Setter
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Setter(AccessLevel.NONE) private Long id;
    private String name;
    private Integer peanutScore;
    private Integer dairyScore;
    private Integer eggScore;
    private Integer AverageScore;
}
