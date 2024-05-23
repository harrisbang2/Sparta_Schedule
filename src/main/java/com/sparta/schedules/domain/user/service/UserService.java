package com.sparta.schedules.domain.user.service;

import com.sparta.schedules.domain.user.dto.LoginRequestDto;
import com.sparta.schedules.domain.user.dto.SignupRequestDto;
import com.sparta.schedules.domain.user.entity.User;
import com.sparta.schedules.domain.user.entity.UserRoleEnum;
import com.sparta.schedules.global.exception.NoSuchUserException;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;

public interface UserService {
    public void signup(SignupRequestDto requestDto);

    public void login(LoginRequestDto requestDto, HttpServletResponse res);
}
