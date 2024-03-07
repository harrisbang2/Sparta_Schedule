package com.sparta.schedules.repositorytest;

import com.sparta.schedules.entity.User;
import com.sparta.schedules.entity.UserRoleEnum;
import com.sparta.schedules.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE,
        connection = EmbeddedDatabaseConnection.H2)
public class UserRepoTest {
    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("유저 생성")
    void createUser(){
        User user = new User();
        user.setUsername("user3");
        user.setPassword("user3");
        user.setEmail("user3@user2.com");
        user.setRole(UserRoleEnum.USER);

        userRepository.save(user);
    }
    @Test
    @DisplayName("유저 검색")
    void findUser(){
        User user = userRepository.findById(1L).orElseThrow();
        assertEquals(user.getId(),1L);
    }
    @Test
    @DisplayName("유저 비번 변경")
    void updateUser(){
        User user = userRepository.findById(1L).orElseThrow();
        user.updatePassword("1235123123");
        User user2 = userRepository.findById(1L).orElseThrow();
        System.out.println(user2.getPassword());
        assertEquals(user.getPassword(),user2.getPassword());
    }
}
