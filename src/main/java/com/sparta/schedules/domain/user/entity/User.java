package com.sparta.schedules.domain.user.entity;

//import com.sparta.schedules.entity.UserRoleEnum;
import com.sparta.schedules.domain.schedule.entity.Schedule;
import com.sparta.schedules.domain.user.dto.SignupRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;


    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @OneToMany
    @JoinColumn(name = "user_id")
    List<Schedule> schedules = new ArrayList<>();

    public User(String username, String password, String email, UserRoleEnum role){
        this.username = username;
        this.password = password;
        this.email = email;
        this.role= role;
    }

    public User(SignupRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
        this.email = requestDto.getEmail();
        if(requestDto.getAdminToken().equals("AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC"))
            this.role = UserRoleEnum.ADMIN;
        else
            this.role = UserRoleEnum.USER;
    }
}
