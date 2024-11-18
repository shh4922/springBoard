package com.hyeonho.board.repository;

import com.hyeonho.board.domain.memver.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRefInf {

    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAllUser();
}
