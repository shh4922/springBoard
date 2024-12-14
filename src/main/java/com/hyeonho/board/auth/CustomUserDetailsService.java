package com.hyeonho.board.auth;

import com.hyeonho.board.domain.Users;
import com.hyeonho.board.repository.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UsersRepository userRepository;

    @Override
    @Transactional
    // 로그인시에 DB에서 유저정보와 권한정보를 가져와서 해당 정보를 기반으로 userdetails.User 객체를 생성해 리턴
    public UserDetails loadUserByUsername(final String username) {

        return userRepository.findByEmail(username)
                .map(user -> createUser(username, user))
                .orElseThrow(() -> new UsernameNotFoundException(username + " -> 데이터베이스에서 찾을 수 없습니다."));
    }

    private org.springframework.security.core.userdetails.User createUser(String username, Users user) {
//        if (!user.isActivated()) {
//            throw new RuntimeException(username + " -> 활성화되어 있지 않습니다.");
//        }

        List<GrantedAuthority> grantedAuthorities = List.of(
                new SimpleGrantedAuthority("ROLE_USER") // 더미 권한을 여기서 추가
        );

        return new org.springframework.security.core.userdetails.User(user.getName(),
                user.getPassword(),
                grantedAuthorities);
    }
}
