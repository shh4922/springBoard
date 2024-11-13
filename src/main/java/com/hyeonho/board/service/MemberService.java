package com.hyeonho.board.service;

import com.hyeonho.board.domain.Member;
import com.hyeonho.board.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member save(Member member) {
        return memberRepository.save(member);
    }

    public List<Member> findAllUser() {
        System.out.println("run members!! on service");
        return  memberRepository.findAllUser();
    }

    public Member findById(Long id) {
        System.out.println("run findById! on Service");
        return memberRepository.findById(id).orElse(null);
    }

    public Optional<Member> findByName(String name) {
        return memberRepository.findByName(name);
    }


}
