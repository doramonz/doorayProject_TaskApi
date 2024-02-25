package com.nhnacademy.doorayProject.dto.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class TaskSimpleInfoListDto {
    private List<TaskSimpleInfoDto> tasks;
}
