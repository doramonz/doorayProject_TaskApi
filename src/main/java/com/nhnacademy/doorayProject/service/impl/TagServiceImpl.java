package com.nhnacademy.doorayProject.service.impl;

import com.nhnacademy.doorayProject.repository.TagRepository;
import com.nhnacademy.doorayProject.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("tagService")
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
}
