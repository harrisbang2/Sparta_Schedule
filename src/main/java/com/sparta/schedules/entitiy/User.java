package com.sparta.schedules.entitiy;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@RequiredArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username",nullable = false)
    private String username;
    @Column(name = "password",nullable = false)
    private String password;
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Schedule> scheduleList = new ArrayList<>();

}
