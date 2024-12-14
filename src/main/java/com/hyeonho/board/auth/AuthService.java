package com.hyeonho.board.auth;

import com.hyeonho.board.domain.Users;
import com.hyeonho.board.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Component
public class AuthService {

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthService(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * token의 사용자 idx를 이용하여 사용자 정보 조회하고, UsernamePasswordAuthenticationToken 생성
     *
     * @param users 사용자
     * @return 사용자 UsernamePasswordAuthenticationToken
     */
    public UsernamePasswordAuthenticationToken getUserAuth(Users users) {
        return new UsernamePasswordAuthenticationToken(
                users.getEmail(),
                users.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(users.getName()))
        );
    }




}
