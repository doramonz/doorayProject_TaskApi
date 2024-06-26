package com.nhnacademy.doorayProject.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "dooray_project_member")
@ToString
public class ProjectMember {

    @EmbeddedId
    private Pk pk;

    @Column(name = "auth")
    private String auth;



    @ManyToOne
    @MapsId("projectId")
    @JoinColumn(name = "project_id")
    private Project project;


    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    @Embeddable
    @ToString
    public static class Pk implements Serializable {

        @Column(name = "user_id")
        private String userId;
        private Integer projectId;
    }
}
