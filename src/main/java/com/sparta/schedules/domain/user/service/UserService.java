package com.sparta.schedules.domain.user.service;

import com.sparta.schedules.domain.user.dto.LoginRequestDto;
import com.sparta.schedules.domain.user.dto.SignupRequestDto;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public void signup(SignupRequestDto requestDto);

    public void login(LoginRequestDto requestDto, HttpServletResponse res);

}
