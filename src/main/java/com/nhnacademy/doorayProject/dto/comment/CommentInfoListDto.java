package com.nhnacademy.doorayProject.dto.comment;

import com.nhnacademy.doorayProject.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class CommentInfoListDto {
    private List<CommentInfoDto> comments;
}
