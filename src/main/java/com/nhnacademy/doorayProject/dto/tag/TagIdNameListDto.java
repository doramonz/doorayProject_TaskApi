package com.nhnacademy.doorayProject.dto.tag;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class TagIdNameListDto {
    List<TagIdNameDto> tags;
}
