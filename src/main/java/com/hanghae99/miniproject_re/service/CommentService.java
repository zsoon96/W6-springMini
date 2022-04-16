package com.hanghae99.miniproject_re.service;

import com.hanghae99.miniproject_re.dto.CommentRequestDto;
import com.hanghae99.miniproject_re.dto.CommentResponseDto;
import com.hanghae99.miniproject_re.dto.StatusResponseDto;
import com.hanghae99.miniproject_re.model.Comment;
import com.hanghae99.miniproject_re.model.Hobby;
import com.hanghae99.miniproject_re.repository.CommentRepository;
import com.hanghae99.miniproject_re.repository.HobbyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final HobbyRepository hobbyRepository;


    public StatusResponseDto postComment(Integer hobbyId,CommentRequestDto commentRequestDto){
        // 게시글이 있는지 확인
        Hobby hobby = hobbyRepository.findById(hobbyId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 없습니다.")
        );
        // 게시글에 있다면 댓글 객체 생성 후 저장
        Comment comment = new Comment(hobbyId,commentRequestDto);
        commentRepository.save(comment);
        // 성공했다는 상태 값으로 전달하기 위해 dto 객체 생성
        StatusResponseDto statusResponseDto = new StatusResponseDto(comment);
        return statusResponseDto;
    }

    public List<CommentResponseDto> getComment(Integer hobbyId){
        Hobby hobby = hobbyRepository.findById(hobbyId).orElseThrow(
                () -> new IllegalArgumentException("게시글이 없습니다.")
        );
        // comment Repo에서 hobbyId를 가지고 찾는 조건을 설정하는 메소드? 정의 후 사용
        List<Comment> commentList = commentRepository.findByHobbyIdOrderByModifiedAt(hobbyId);
        // comment 리스트에서 필요한 내용만 담을 dto 리스트 생성
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
        // 반복문을 통해 comment 리스트에서 필요한 id, comment, nickname만 순차적으로 빼서 dto에 담아주고, 그 dto는 dto로 리스트에 차곡차곡 추가
        for (Comment comment : commentList) {
            Integer id = comment.getId();
            String c = comment.getComment();
            String n = comment.getNickname();
            CommentResponseDto commentResponseDto = new CommentResponseDto(id,c,n);
            commentResponseDtoList.add(commentResponseDto);
        }
        return commentResponseDtoList;
    }

    public StatusResponseDto putComment(Integer commentId, CommentRequestDto commentRequestDto){
        // commentId를 통해 comment 객체 찾기
        Comment comment = commentRepository.findById(commentId).orElseThrow( ()-> new IllegalArgumentException("댓글이 없습니다."));
        // 찾은 comment 객체를 dto 내용으로 업데이트 해주기
        comment.update(commentRequestDto);
        // 업데이트한 comment
        commentRepository.save(comment);
        StatusResponseDto statusResponseDto = new StatusResponseDto(commentId, commentRequestDto);
        return statusResponseDto;
    }

    public StatusResponseDto delComment(Integer commentId){
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()-> new IllegalArgumentException("댓글이 없습니다.")
        );
        commentRepository.deleteById(commentId);
        StatusResponseDto statusResponseDto = new StatusResponseDto(commentId, comment);
        return statusResponseDto;
    }
}
