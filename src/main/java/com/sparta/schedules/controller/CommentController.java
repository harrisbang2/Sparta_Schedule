package com.sparta.schedules.controller;

import com.sparta.schedules.dto.CommentRequestDto;
import com.sparta.schedules.dto.CommentResponseDto;
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
    @Autowired
    private CommentService service;
    /// add comments
    @PostMapping("/comment")
    public ResponseEntity<?> CreateComment(@RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
       return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createComment(requestDto,userDetails.getUser()));
    }

    /// get comments
    @GetMapping("/comment")
    public List<CommentResponseDto> getComments(@RequestParam(value = "ID") Long id){
        return service.getComments(id);
    }
    ///
    @PutMapping("/comment/{id}")
    public ResponseEntity<?> updateComment(@PathVariable(name = "id") Long id, @RequestBody CommentRequestDto requestDto,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.updateComment(id,requestDto,userDetails.getUser()));
    }
    // deleting the item.
    @DeleteMapping("/comment/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable(name = "id") Long id,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.deleteComment(id,userDetails.getUser()));
    }
}
