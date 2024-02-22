package com.nhnacademy.doorayProject.entity;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dooray_milestone")
public class MileStone {
    @Id
    @Column(name = "milestone_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mileStoneId;
    @Column(name = "milestone_name")
    private String mileStoneName;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}
