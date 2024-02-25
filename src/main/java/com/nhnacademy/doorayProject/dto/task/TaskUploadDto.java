package com.nhnacademy.doorayProject.dto.task;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class TaskUploadDto {
    private String taskTitle;
    private String taskContent;
    private LocalDateTime endDate;
}
