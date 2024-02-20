package com.sparta.schedules.servicetest;

import com.sparta.schedules.dto.LoginRequestDto;
import com.sparta.schedules.dto.SignupRequestDto;
import com.sparta.schedules.entity.User;
import com.sparta.schedules.entity.UserRoleEnum;
import com.sparta.schedules.exception.NoSuchUserException;
import com.sparta.schedules.jwt.JwtUtil;
import com.sparta.schedules.repository.UserRepository;
import com.sparta.schedules.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Autowired
     PasswordEncoder passwordEncoder;
    @Autowired
     UserRepository loginRepo;
     private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";
    @Mock
    UserRepository mockRepo;

    JwtUtil jwtUtil;
    //


    @Test
    @DisplayName("유저 생성")
    void create() {
        //given
        boolean hasError = false;
        SignupRequestDto requestDto = new SignupRequestDto();
        requestDto.setUsername("user");
        requestDto.setPassword("password");
        requestDto.setEmail("harrisbang98@gmail.com");
        User user = new User(requestDto);
        //when
        try {
            String username = requestDto.getUsername();
            String password = passwordEncoder.encode(requestDto.getPassword());

            // 회원 중복 확인
            Optional<User> checkUsername = mockRepo.findByUsername(username);
            if (checkUsername.isPresent()) {
                throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
            }
            // email 중복확인
            String email = requestDto.getEmail();
            Optional<User> checkEmail = mockRepo.findByEmail(email);
            if (checkEmail.isPresent()) {
                throw new IllegalArgumentException("중복된 Email 입니다.");
            }
            // 사용자 ROLE 확인
            UserRoleEnum role = UserRoleEnum.USER;
            if (requestDto.isAdmin()) {
                if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                    throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
                }
                user.setRole(UserRoleEnum.ADMIN);
            }
            mockRepo.save(user);
        }catch(IllegalArgumentException e){
            hasError = true;
        }
        assertFalse(hasError);
    }
    @Test
    @DisplayName("로그인")
    void login(){
        //given
        boolean hasError = false;
        String username = "user";
        String password = "user";
        User user= new User();
        //when
        try{
            user = loginRepo.findByUsername(username).orElseThrow(
                    NoSuchUserException::new
            );
            // 비밀번호 확인
            if (!passwordEncoder.matches(password, user.getPassword())) {
                throw new NoSuchUserException("아이디/비밀번호가 일치하지 않습니다.");
            }
        }
        catch (Exception e){
            hasError = true;
        }
        //then
        assertTrue(user.getEmail().equals("harrisbang98@gmail.com"));
        assertFalse(hasError);
    }
}
