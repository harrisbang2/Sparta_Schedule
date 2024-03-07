package com.sparta.schedules.entity;

//import com.sparta.schedules.entity.UserRoleEnum;
import com.sparta.schedules.dto.SignupRequestDto;
import com.sparta.schedules.dto.UserRequestDto;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    List<Schedule> schedules = new ArrayList<>();

    @Builder
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
    @Transactional
    public void updatePassword(String password) {
        this.password = password;
    }
}