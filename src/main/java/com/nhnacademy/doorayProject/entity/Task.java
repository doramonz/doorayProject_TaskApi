package com.nhnacademy.doorayProject.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dooray_task")
public class Task {
    public enum Status {
        할일, 진행중, 완료
    }
    @Id
    @Column(name = "task_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer taskId;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "milestone_id")
    private Integer mileStoneId;
    @Column(name = "tag_id")
    private String taskTitle;
    @Column(name = "task_content")
    private String taskContent;
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "project_id")
    private Project project;

}
