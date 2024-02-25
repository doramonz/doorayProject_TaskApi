package com.nhnacademy.doorayProject.entity;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dooray_task")
@ToString
public class Task {
    public enum Status {
        할일, 진행중, 완료
    }
    @Id
    @Column(name = "task_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer taskId;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @Column(name = "user_id")
    private String userId;

    @ManyToOne
    @JoinColumn(name = "milestone_id")
    private MileStone mileStoneId;

    @Column(name = "task_title")
    private String taskTitle;
    @Column(name = "task_content")
    private String taskContent;
    @Enumerated(EnumType.STRING)
    private Status status;

}
