package com.sparta.schedules.controller;

import com.sparta.schedules.Dto.LoginRequestDto;
import com.sparta.schedules.Dto.SignupRequestDto;
import com.sparta.schedules.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/login-page")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/user/signup")
    public String signupPage() {
        return "signup";
    }
    //
    @PostMapping("/user/signup")
    public String signup(SignupRequestDto requestDto) {
        userService.signup(requestDto);
        return "redirect:/api/user/login-page";
    }
    // 로그임
    @PostMapping("/user/login")
    public String login(LoginRequestDto requestDto, HttpServletResponse res){
        try {
            userService.login(requestDto,res);
        } catch (Exception e) {
            return "/api/user/login-page";
        }
        return "redirect:/";
    }
}