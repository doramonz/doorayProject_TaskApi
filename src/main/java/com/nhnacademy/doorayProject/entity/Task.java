package com.nhnacademy.doorayProject.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dooray_task")
@ToString
@Getter
@Setter
@Data
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

    @ManyToOne
    @JoinColumn(name = "milestone_id")
    private MileStone mileStoneId;

    @Column(name = "task_title")
    private String taskTitle;
    @Column(name = "task_content")
    private String taskContent;
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "create_at")
    private LocalDateTime createAt;
    @Column(name = "end_date")
    private LocalDateTime endDate;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "project_id")
    private Project project;
}
