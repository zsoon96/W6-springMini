package com.hanghae99.miniproject_re.dto;

import com.hanghae99.miniproject_re.model.Comment;
import com.hanghae99.miniproject_re.model.Hobby;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
public class StatusResponseDto {
    private String status = "true";
    private String http = String.valueOf(HttpStatus.OK);
    private String message;

    public StatusResponseDto(Hobby hobby) {
        this.message = "취미 등록 성공!";
    }

    public StatusResponseDto(Integer hobbyId, Hobby hobby) {
        this.message = "게시글 수정 성공!";
    }

    public StatusResponseDto(Integer hobbyId) {
        this.message = "게시글 삭제 성공!";
    }

    public StatusResponseDto(Comment comment) {
        this.message = "댓글 작성 성공!";
    }

    public StatusResponseDto(Integer commentId, CommentRequestDto commentRequestDto) {
        this.message = "댓글 수정 성공!";
    }

    public StatusResponseDto(Integer commentId, Comment comment){
        this.message = "댓글 삭제 성공!";
    }

}
