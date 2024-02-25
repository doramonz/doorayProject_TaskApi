package com.nhnacademy.doorayProject.dto;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;


public interface SimpleTaskDto {
    Integer getTaskId();
    String getTaskTitle();
    String getUserId();
}
