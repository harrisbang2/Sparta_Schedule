package com.sparta.schedules.entity;


import com.sparta.schedules.dto.CommentRequestDto;
import com.sparta.schedules.dto.CommentResponseDto;
import com.sparta.schedules.dto.ScheduleRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "comment")
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "comment", nullable = false)
    private String comment;
    @ManyToOne
    @JoinColumn(name = "schedule")
    private Schedule schedule;
    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    public Comment(CommentRequestDto requestDto, User user) {
        this.comment = requestDto.getComment();
        this.user = user;
    }

    public Comment(CommentRequestDto requestDto, Schedule sc, User user) {
        this.comment = requestDto.getComment();
        this.schedule = sc;
        this.user = user;
    }

    public void update(CommentRequestDto requestDto) {
        this.comment = requestDto.getComment();
    }
}
