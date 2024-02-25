package com.nhnacademy.doorayProject.dto;

import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class TaskUpdateDto {
    String userId;
    String taskTitle;
    String taskContent;
}
