package com.nhnacademy.doorayProject.dto.project;

import com.nhnacademy.doorayProject.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ProjectListDto {
    private List<Project> projects;
}
