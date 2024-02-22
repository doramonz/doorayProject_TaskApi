package com.nhnacademy.doorayProject.controller;

import com.nhnacademy.doorayProject.dto.TagNameDto;
import com.nhnacademy.doorayProject.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor

public class TagController {

    private final TagService tagService;

    @GetMapping("{tagId}")
    public TagNameDto getTag(@PathVariable(name = "tagId") Integer tagId) {
        return (TagNameDto) tagService.getTag(tagId);
    }




}
