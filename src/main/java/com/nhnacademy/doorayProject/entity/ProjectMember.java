package com.nhnacademy.doorayProject.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "dooray_project_member")
public class ProjectMember {

    @EmbeddedId
    private Pk pk;

    @Column(name = "auth")
    private String auth;



    @ManyToOne
    @MapsId("projectId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "project_id")
    private Project project;


    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    @Embeddable
    public static class Pk implements Serializable {

        @Column(name = "user_id")
        private String userId;
        private Integer projectId;
    }
}
