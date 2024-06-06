package com.sparta.schedules.global.security;


import com.sparta.schedules.domain.user.entity.User;
import com.sparta.schedules.domain.user.entity.UserRoleEnum;
import com.sparta.schedules.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not Found " + username));

        return new UserDetailsImpl(user);
    }

    public UserDetails getUserDetailsFromJwt(String username) throws UsernameNotFoundException {
        User user = new User();
        user.setId(Long.valueOf(username));
        user.setRole(UserRoleEnum.USER);
        return new UserDetailsImpl(user);
    }
}
