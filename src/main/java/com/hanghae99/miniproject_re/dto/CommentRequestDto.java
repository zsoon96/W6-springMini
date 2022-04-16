package com.hanghae99.miniproject_re.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequestDto {
    private String comment;
    private String nickname;
    private int hobbyId;
}
