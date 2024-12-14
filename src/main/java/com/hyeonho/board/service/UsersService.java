package com.hyeonho.board.service;

import com.hyeonho.board.auth.JwtTokenProvider;
import com.hyeonho.board.domain.Users;
import com.hyeonho.board.dto.user.LoginResponseDTO;
import com.hyeonho.board.repository.UsersRepository;
import com.hyeonho.board.util.DefaultRes;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.http.HttpHeaders;
import java.util.HashMap;
import java.util.List;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UsersService(UsersRepository usersRepository, PasswordEncoder passwordEncoder, AuthenticationManagerBuilder authenticationManagerBuilder, JwtTokenProvider jwtTokenProvider) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.jwtTokenProvider = jwtTokenProvider;
    }


    public List<Users> getAllUser() {
        return usersRepository.findAll();
    }

    public Users register(Users users) {
        try {
            if(isEmailDuplicate(users.getEmail())) {
                throw new IllegalArgumentException("사용중인 이메일");
            }

            String encodePassword = passwordEncoder.encode(users.getPassword());

            Users newUser = new Users();
            newUser.setEmail(users.getEmail());
            newUser.setName(users.getName());
            newUser.setPassword(encodePassword);

            return usersRepository.save(newUser);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public LoginResponseDTO login(String email, String password) {
        Users user = usersRepository.findByEmail(email)
                .orElseThrow(()-> new IllegalArgumentException("잘못된 email 입니다."));

        if(!passwordEncoder.matches(password,user.getPassword())) {
            throw new IllegalArgumentException("잘못된 password 입니다.");
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,password);
        System.out.println(email);
        System.out.println(password);
        System.out.println(authenticationToken);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication); // 이게 뭐하는애인지 모르겠음
        String jwt = jwtTokenProvider.createToken(authentication);

        LoginResponseDTO res = new LoginResponseDTO();
        res.setAcessToken(jwt);

        return res;
    }


    public boolean isEmailDuplicate(String email) {
        return usersRepository.findByEmail(email).isPresent();
    }

    public boolean isTrueUser(Long id) {
        return usersRepository.findById(id).isPresent();
    }

    public Users findByEmail(String email) {
        return usersRepository.findByEmail(email)
                .orElseThrow(()-> new IllegalArgumentException("그런 이메일 하는애 없음ㅋㅋ"));
    }

}
