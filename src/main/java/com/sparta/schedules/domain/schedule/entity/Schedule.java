package com.sparta.schedules.domain.schedule.entity;

import com.sparta.schedules.domain.schedule.dto.ScheduleRequestDto;
import com.sparta.schedules.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "schedule",indexes = {@Index(name = "id", columnList = "id")})
@NoArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "contents", nullable = false)
    private String contents;
    @Column(name = "date", nullable = false)
    private LocalDate date;
    //
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



    public Schedule(ScheduleRequestDto requestDto, User user) {
        this.contents = requestDto.getContents();
        this.date = requestDto.getDate();
        this.user = user;
    }

    public Schedule(ScheduleRequestDto requestDto) {
        this.contents = requestDto.getContents();
        this.date = requestDto.getDate();
    }

    public void update(ScheduleRequestDto requestDto) {
        this.contents = requestDto.getContents();
        this.date = requestDto.getDate();
    }
    public void update(String s) {
        this.contents = s;
        this.date = LocalDate.now();
    }
}
