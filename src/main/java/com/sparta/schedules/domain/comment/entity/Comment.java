package com.sparta.schedules.domain.comment.entity;


import com.sparta.schedules.domain.comment.dto.CommentRequestDto;
import com.sparta.schedules.domain.schedule.entity.Schedule;
import com.sparta.schedules.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public Comment(CommentRequestDto requestDto, Schedule sc, User user) {
        this.comment = requestDto.getComment();
        this.schedule = sc;
        this.user = user;
    }

    public void update(CommentRequestDto requestDto) {
        this.comment = requestDto.getComment();
    }

    public void update(String s) {
        this.comment = s;
    }
}
