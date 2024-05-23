package com.sparta.schedules.domain.comment.controller;

import com.sparta.schedules.domain.comment.dto.CommentRequestDto;
import com.sparta.schedules.domain.comment.dto.CommentResponseDto;
import com.sparta.schedules.domain.comment.service.CommentServiceImpl;
import com.sparta.schedules.global.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {
    private CommentServiceImpl service;

    @Autowired
    public CommentController(CommentServiceImpl service) {
        this.service = service;
    }

    /// add comment
    @PostMapping("/comment")
    public ResponseEntity<?> CreateComment(@RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
       return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createComment(requestDto,userDetails.getUser()));
    }

    /// get comments
    @GetMapping("/comment/{id}")
    public List<CommentResponseDto> getCommentsBySchedule(@PathVariable(value = "id") Long id,@AuthenticationPrincipal UserDetailsImpl userDetails){
        return service.getComments(id);
    }
    /// edit comment
    @PutMapping("/comment/{id}")
    public ResponseEntity<?> updateComment(@PathVariable(name = "id") Long id, @RequestBody CommentRequestDto requestDto,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.updateComment(id,requestDto,userDetails.getUser()));
    }
    // deleting comment.
    @DeleteMapping("/comment/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable(name = "id") Long id,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.deleteComment(id,userDetails.getUser()));
    }
}
