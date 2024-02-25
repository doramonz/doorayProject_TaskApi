package com.nhnacademy.doorayProject.dto.task;

import com.nhnacademy.doorayProject.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class TaskListDto {
    private List<Task> tasks;
}
