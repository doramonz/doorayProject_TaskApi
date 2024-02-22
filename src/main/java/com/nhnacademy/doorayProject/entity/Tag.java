package com.nhnacademy.doorayProject.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "dooray_tag")
public class Tag {
    @Id
    @Column(name = "tag_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer tagId;

    @Column(name = "tag_name")
    private String tagName;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project projectId;


}
