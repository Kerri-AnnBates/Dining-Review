package com.bates.diningreview.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Restaurant")
@Getter
@Setter
@RequiredArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String zipCode;
    private Integer peanutScore;
    private Integer dairyScore;
    private Integer eggScore;
    private Integer overallScore;
}
