package com.nhnacademy.doorayProject.dto.task;

import com.nhnacademy.doorayProject.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TaskSimpleInfoDto {
    private Integer taskId;
    private String title;
    private String createUserId;
    private Task.Status status;
}
