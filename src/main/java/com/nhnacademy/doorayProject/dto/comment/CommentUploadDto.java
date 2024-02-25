package com.nhnacademy.doorayProject.dto.comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CommentUploadDto {
    private String userId;
    private String content;
}
