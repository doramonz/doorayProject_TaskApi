package com.nhnacademy.doorayProject.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimpleTaskDto {
    Integer taskId;
    String taskTitle;
    String userId;
}
