package com.hyeonho.board.controller;

import com.hyeonho.board.domain.memver.Member;
import com.hyeonho.board.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/users")
    public List<Member> findAll() {
        System.out.println("run members!! on Controller");
        return memberService.findAllUser();
    }


    @GetMapping("/user/{id}")
    public Member findById(@PathVariable Long id) {
        System.out.println("run findById!");
        return memberService.findById(id);
    }


}
