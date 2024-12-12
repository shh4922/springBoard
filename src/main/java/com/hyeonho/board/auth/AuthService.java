package com.hyeonho.board.auth;

import com.hyeonho.board.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Component
public class AuthService {

    private final UsersService usersService;

    @Autowired
    public AuthService(UsersService usersService) {
        this.usersService = usersService;
    }

    /**
     * token의 사용자 idx를 이용하여 사용자 정보 조회하고, UsernamePasswordAuthenticationToken 생성
     *
     * @param username 사용자 idx
     * @return 사용자 UsernamePasswordAuthenticationToken
     */
    public UsernamePasswordAuthenticationToken getUserAuth(String username) {
        var userInfo = usersService.findByEmail(username);

        return new UsernamePasswordAuthenticationToken(
                userInfo.getEmail(),
                userInfo.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(userInfo.getName()))
        );

    }
}
