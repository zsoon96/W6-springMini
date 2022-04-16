package com.hanghae99.miniproject_re.model;

import com.hanghae99.miniproject_re.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Comment extends Timestamped {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private int hobbyId;

    public Comment(Integer hobbyId, CommentRequestDto commentRequestDto) {
        this.comment = commentRequestDto.getComment();
        this.nickname = commentRequestDto.getNickname();
        this.hobbyId = hobbyId;
    }


    public void update(CommentRequestDto commentRequestDto) {
        this.comment = commentRequestDto.getComment();
        this.nickname = commentRequestDto.getNickname();
    }
}
