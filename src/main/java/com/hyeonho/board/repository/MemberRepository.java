package com.hyeonho.board.repository;


import com.hyeonho.board.domain.memver.Member;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberRepository implements MemberRefInf {

    private final EntityManager em;

    public MemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        System.out.println("run findById! on Repository");
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Member> findAllUser() {
        System.out.println("run members!! on refo");
        List<Member> list = new ArrayList<>();

        Member member = new Member();
        member.setId(2L);
        member.setUsername("userName");
        member.setName("name");
        member.setPassword("pass");

        list.add(member);
//        return  list;
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}
