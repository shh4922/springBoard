package com.hyeonho.board.controller;
import com.hyeonho.board.auth.JwtTokenProvider;
import com.hyeonho.board.domain.Users;
import com.hyeonho.board.dto.user.LoginResponseDTO;
import com.hyeonho.board.service.UsersService;
import com.hyeonho.board.util.DefaultRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class UsersController {

    private final UsersService usersService;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UsersController(UsersService usersService, JwtTokenProvider jwtTokenProvider) {
        this.usersService = usersService;
        this.jwtTokenProvider = jwtTokenProvider;
    }


    @GetMapping("")
    public List<Users> getAllUser() {
        List<Users> usersList = usersService.getAllUser();
        return usersList;
    }

    @PostMapping("/login")
    public DefaultRes<LoginResponseDTO> login(@RequestBody Map<String, Object> request) {
        try {
            String email = (String) request.get("email");
            String password = (String) request.get("password");

            if(email == null || password == null) {
                return DefaultRes.res(400, "email|password 비었음");
            }

            LoginResponseDTO user = usersService.login(email,password);
            if(user == null) {
                return DefaultRes.res(400,"잘못된 아이디 혹은 비번", null);
            }

            return DefaultRes.res(200,"로그인성공",user);
        } catch (IllegalArgumentException e) {
            return DefaultRes.res(400, e.getMessage());
        }
    }

    @PostMapping("/register")
    public DefaultRes<?> register(@RequestBody Users user) {
        try {
            Users savedUser = usersService.register(user);
            return DefaultRes.res(201,"가입성공", savedUser);
        } catch (IllegalArgumentException e) {
            return DefaultRes.res(400, e.getMessage());
        } catch (Exception e) {
            return DefaultRes.res(500, e.getMessage());
        }
    }

    @PostMapping("/duplicate/email")
    public DefaultRes<?> isEmailDuplicate(@RequestBody String email) {
        try {
            if(!usersService.isEmailDuplicate(email)) {
                return DefaultRes.res(409,"이미있는 이메일", email);
            }
            return DefaultRes.res(200,"사용가능한 이메일", email);
        } catch (Exception e) {
            return DefaultRes.res(500,"서버에러", e);
        }
    }
}
