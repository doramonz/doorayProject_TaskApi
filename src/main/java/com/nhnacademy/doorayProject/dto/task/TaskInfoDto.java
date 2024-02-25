package com.nhnacademy.doorayProject.dto.task;

import com.nhnacademy.doorayProject.entity.MileStone;
import com.nhnacademy.doorayProject.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskInfoDto {
    private String title;
    private String content;
    private String createUserId;
    private Task.Status status;
    private LocalDateTime createAt;
    private LocalDateTime endDate;
    private MileStone mileStone;
}
