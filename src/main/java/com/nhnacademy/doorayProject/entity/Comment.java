package com.nhnacademy.doorayProject.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
@EqualsAndHashCode
@Table(name = "dooray_commnet")
public class Comment {
    @Id
    @Column(name = "commnet_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer commentId;

    @Column(name = "user_id")
    private String user;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;


    @Column(name = "comment_content")
    private String commentContent;
}
