package com.sparta.schedules.service;

import com.sparta.schedules.dto.LoginRequestDto;
import com.sparta.schedules.dto.SignupRequestDto;
import com.sparta.schedules.dto.UserRequestDto;
import com.sparta.schedules.entity.User;
import com.sparta.schedules.entity.UserRoleEnum;
import com.sparta.schedules.exception.NoSuchUserException;
import com.sparta.schedules.jwt.JwtUtil;
import com.sparta.schedules.repository.UserRepository;
import com.sparta.schedules.repository.projectionInterface.UserProfile;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.crypto.password.PasswordEncoder;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

    @Service
    public class UserService {

        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;
        private final JwtUtil jwtUtil;

        public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
            this.userRepository = userRepository;
            this.passwordEncoder = passwordEncoder;
            this.jwtUtil = jwtUtil;
        }

      public void signup(SignupRequestDto requestDto) {
            String username = requestDto.getUsername();
            String password = passwordEncoder.encode(requestDto.getPassword());

            // 회원 중복 확인
            Optional<User> checkUsername = userRepository.findByUsername(username);
            if (checkUsername.isPresent()) {
                throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
            }

            // email 중복확인
            String email = requestDto.getEmail();
            Optional<UserProfile> checkEmail = userRepository.findByEmail(email);
            if (checkEmail.isPresent()) {
                throw new IllegalArgumentException("중복된 Email 입니다.");
            }

            // 사용자 ROLE 확인
            UserRoleEnum role = UserRoleEnum.USER;
            if (requestDto.isAdmin()) {
              // ADMIN_TOKEN
              String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";
              if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                    throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
                }
                role = UserRoleEnum.ADMIN;
            }

            // 사용자 등록
            User user = new User(username, password, email, role);
            userRepository.save(user);
        }

        public void login(LoginRequestDto requestDto, HttpServletResponse res) {
            String username = requestDto.getUsername();
            String password = requestDto.getPassword();

            // 사용자 확인
            User user = userRepository.findByUsername(username).orElseThrow(
                    NoSuchUserException::new
            );

            // 비밀번호 확인
            if (!passwordEncoder.matches(password, user.getPassword())) {
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
            }

//            // JWT 생성 및 쿠키에 저장 후 Response 객체에 추가
//            String token = jwtUtil.createToken(user.getUsername(), user.getRole());
//            jwtUtil.addJwtToCookie(token, res);

            // 유저정보에서 이름값을 가져와서 해당 유저의 권한과 함께 넣어 토큰을 만듬.
            String token = jwtUtil.createToken(user.getId(),user.getUsername(),user.getRole());
//            res.addCookie(new Cookie(JwtUtil.AUTHORIZATION_HEADER,token));
            res.setHeader(JwtUtil.AUTHORIZATION_HEADER, token);
        }

        // 로그 아웃
        public void userLogout(HttpServletResponse httpServletResponse) {
            httpServletResponse.setHeader("Authorization", null);
        }

        @Transactional
        public boolean update(UserRequestDto userRequestDto, User user){
            User updateUser = userRepository.findByUsername(user.getUsername()).orElseThrow(
                NoSuchUserException::new
            );
            updateUser.updatePassword(passwordEncoder.encode(userRequestDto.getPassword()));
            return true;
        }
    }
