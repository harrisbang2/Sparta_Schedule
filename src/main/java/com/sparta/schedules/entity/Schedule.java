package com.sparta.schedules.entity;

import com.sparta.schedules.dto.ScheduleRequestDto;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Setter
@Table(name = "schedules")
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "contents", nullable = false)
    private String contents;
    @Column(name = "date", nullable = false)
    private LocalDate date;
    //
    @ManyToOne(fetch = FetchType.LAZY)
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
    @Transactional
    public void update(ScheduleRequestDto requestDto) {
        this.contents = requestDto.getContents();
        this.date = requestDto.getDate();
    }
    @Transactional
    public void update(String s) {
        this.contents = s;
        this.date = LocalDate.now();
    }
}