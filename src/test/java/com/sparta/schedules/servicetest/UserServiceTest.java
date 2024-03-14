package com.sparta.schedules.servicetest;

import com.sparta.schedules.dto.LoginRequestDto;
import com.sparta.schedules.dto.SignupRequestDto;
import com.sparta.schedules.entity.User;
import com.sparta.schedules.exception.jwt.JwtUtil;
import com.sparta.schedules.repository.UserRepository;
import com.sparta.schedules.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
     PasswordEncoder passwordEncoder;
    @Mock
     UserRepository loginRepo;
     private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";
    @Mock
    HttpServletResponse response;
    @Mock
    JwtUtil jwtUtil;
    //


    @Test
    @DisplayName("유저 생성")
    void create() {
        //given
        boolean hasError = false;
        SignupRequestDto requestDto = new SignupRequestDto();
        requestDto.setUsername("usertest");
        requestDto.setPassword("password");
        requestDto.setEmail("harrisbang98@gmail.com");

        //when
        UserService userService = new UserService(loginRepo,passwordEncoder,jwtUtil);
        try{
            given(passwordEncoder.encode(requestDto.getPassword())).willReturn("password");
            userService.signup(requestDto);
        }catch (Exception e){
            hasError = true;
        }
        //then
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
        UserService userService = new UserService(loginRepo,passwordEncoder,jwtUtil);
        LoginRequestDto requestDto = new LoginRequestDto();
        requestDto.setUsername(username);
        requestDto.setPassword(password);
        //when
        try{
            given(loginRepo.findByUsername(username)).willReturn(Optional.of(user));
            given(!passwordEncoder.matches(password, user.getPassword())).willReturn(true);
            userService.login(requestDto,response);

        }catch (Exception e){
            hasError = true;
        }


        //then
        assertFalse(hasError);
    }
}
