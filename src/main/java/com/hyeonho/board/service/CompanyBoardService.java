package com.hyeonho.board.service;

import com.hyeonho.board.domain.memver.Member;
import com.hyeonho.board.domain.company.CompanyBoard;
import com.hyeonho.board.domain.company.CompanyBoardDTO;
import com.hyeonho.board.domain.company.CompanyCategory;
import com.hyeonho.board.repository.CompanyBoardRepo;
import com.hyeonho.board.repository.CompanyCategoryRepo;
import com.hyeonho.board.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CompanyBoardService {

    private final CompanyBoardRepo companyBoardRepo;
    private final MemberRepository memberRepository;
    private final CompanyCategoryRepo companyCategoryRepo;

    @Autowired
    public CompanyBoardService(CompanyBoardRepo companyBoardRepo, MemberRepository memberRepository, CompanyCategoryRepo companyCategoryRepo) {
        this.companyBoardRepo = companyBoardRepo;
        this.memberRepository = memberRepository;
        this.companyCategoryRepo = companyCategoryRepo;
    }

    public List<CompanyBoard> findAllPost() {
        return companyBoardRepo.findAll();
    }

    public CompanyBoard createPost(CompanyBoardDTO companyBoardDTO) {
        System.out.println(companyBoardDTO);

        Member member = memberRepository
                .findById(companyBoardDTO.getMemberId())
                .orElseThrow(() -> new RuntimeException("Member not found"));

        CompanyCategory category = companyCategoryRepo
                .findById(companyBoardDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        System.out.println(member);
        System.out.println(category);

        CompanyBoard post = new CompanyBoard();
        post.setTitle(companyBoardDTO.getTitle());
        post.setContent(companyBoardDTO.getContent());
        post.setPoint(companyBoardDTO.getPoint());
        post.setMember(member); // 연관관계 설정
        post.setCategory(category); // 연관관계 설정

        return companyBoardRepo.save(post);
    }
}
