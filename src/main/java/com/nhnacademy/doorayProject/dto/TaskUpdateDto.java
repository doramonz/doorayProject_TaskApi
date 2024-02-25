package com.nhnacademy.doorayProject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaskUpdateDto {
    String userId;
    String taskTitle;
    String taskContent;
}
