package com.hanghae99.miniproject_re.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
    private int id;
    private String comment;
    private String nickname;

    public CommentResponseDto(Integer id, String c, String n) {
        this.id = id;
        this.comment = c;
        this.nickname = n;
    }
}
