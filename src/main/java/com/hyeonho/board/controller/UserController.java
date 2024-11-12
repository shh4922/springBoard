package com.hyeonho.board.controller;

import com.hyeonho.board.domain.User;
import com.hyeonho.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> findAll() {
        return userService.findAllUser();
    }


//    @GetMapping("/user/${id}")
//    public User findById(Long id) {
//        return userService.findById(id);
//    }

    @GetMapping("/test")
    public String test() {
        return "ㅎㅇㅎㅇ";
    }


}
