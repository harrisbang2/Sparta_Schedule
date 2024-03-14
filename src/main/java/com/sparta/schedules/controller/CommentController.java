package com.sparta.schedules.controller;

import com.sparta.schedules.dto.CommentRequestDto;
import com.sparta.schedules.dto.CommentResponseDto;
import com.sparta.schedules.dto.ResponseDto;
import com.sparta.schedules.repository.projectionInterface.CommentList;
import com.sparta.schedules.security.UserDetailsImpl;
import com.sparta.schedules.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {
    private CommentService service;

    @Autowired
    public CommentController(CommentService service) {
        this.service = service;
    }

    /// add comments
    @PostMapping("/comment")
    public ResponseEntity<ResponseDto<CommentResponseDto>> CreateComment(
        @RequestBody CommentRequestDto requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails
    )
    {
       return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseDto.<CommentResponseDto>builder()
                    .data(service.createComment(requestDto,userDetails.getUser()))
                    .statusCode(201)
                    .build());
    }

    /// get comments
    @GetMapping("/comment/{id}")
    public ResponseEntity<ResponseDto<List<CommentList>>> getCommentsBySchedule(
        @PathVariable(value = "id") Long id
    ){
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ResponseDto.<List<CommentList>>builder()
                .data(service.getComments(id))
                .statusCode(200)
                .build());
    }
    ///
    @PutMapping("/comment/{id}")
    public ResponseEntity<ResponseDto<Long>> updateComment(
        @PathVariable(name = "id") Long id,
        @RequestBody CommentRequestDto requestDto,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(ResponseDto.<Long>builder()
                .data(service.updateComment(id,requestDto,userDetails.getUser()))
                .statusCode(201)
                .build());
    }
    // deleting the item.
    @DeleteMapping("/comment/{id}")
    public ResponseEntity<ResponseDto<?>> deleteComment(@PathVariable(name = "id") Long id,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ResponseDto.<Long>builder()
                .data(service.deleteComment(id,userDetails.getUser()))
                .statusCode(201)
                .build());
    }
}
