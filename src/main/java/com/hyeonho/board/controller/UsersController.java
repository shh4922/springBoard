package com.hyeonho.board.controller;
import com.hyeonho.board.domain.Users;
import com.hyeonho.board.service.UsersService;
import com.hyeonho.board.util.DefaultRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }


    @GetMapping("")
    public List<Users> getAllUser() {
        List<Users> usersList = usersService.getAllUser();
        return usersList;
    }

    @PostMapping("/register")
    public DefaultRes<?> register(@RequestBody Users user) {
        try {
            Users savedUser = usersService.register(user);
            return DefaultRes.res(200,"가입성공", savedUser);
        } catch (IllegalArgumentException e) {
            return DefaultRes.res(400, e.getMessage());
        } catch (Exception e) {
            System.out.println(e);
            return DefaultRes.res(500, e.getMessage());
        }
    }
}
