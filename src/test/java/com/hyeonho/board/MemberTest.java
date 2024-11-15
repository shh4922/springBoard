package com.hyeonho.board;

import com.hyeonho.board.domain.Member;
import com.hyeonho.board.repository.MemberRepository;
import com.hyeonho.board.service.MemberService;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class MemberTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

}
