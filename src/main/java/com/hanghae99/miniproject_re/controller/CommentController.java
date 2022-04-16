package com.hanghae99.miniproject_re.controller;

import com.hanghae99.miniproject_re.dto.CommentRequestDto;
import com.hanghae99.miniproject_re.dto.CommentResponseDto;
import com.hanghae99.miniproject_re.dto.StatusResponseDto;
import com.hanghae99.miniproject_re.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("{hobbyId}/comment")
    public StatusResponseDto createComment(@PathVariable Integer hobbyId, @RequestBody CommentRequestDto commentRequestDto){
        return commentService.postComment(hobbyId, commentRequestDto);
    }

    @GetMapping("/{hobbyId}/comments")
    public List<CommentResponseDto> showComment(@PathVariable Integer hobbyId) {
        return commentService.getComment(hobbyId);
    }

    @PutMapping("/{hobbyId}/comments/{commentId}")
    public StatusResponseDto updateComment(@PathVariable Integer commentId, @RequestBody CommentRequestDto commentRequestDto){
        return commentService.putComment(commentId, commentRequestDto);
    }

    @DeleteMapping("/{hobbyId}/comments/{commentId}")
    public StatusResponseDto deleteComment(@PathVariable Integer commentId){
        return commentService.delComment(commentId);
    }
}
