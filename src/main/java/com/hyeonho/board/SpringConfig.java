package com.hyeonho.board;

import com.hyeonho.board.repository.CompanyBoardRepo;
import com.hyeonho.board.repository.CompanyCategoryRepo;
import com.hyeonho.board.repository.MemberRepository;
import com.hyeonho.board.service.CompanyBoardService;
import com.hyeonho.board.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;
    private final CompanyBoardRepo companyBoardRepo;
    private final CompanyCategoryRepo companyCategoryRepo;

    @Autowired
    public SpringConfig(MemberRepository memberRepository, CompanyBoardRepo companyBoardRepo, CompanyCategoryRepo companyCategoryRepo) {
        this.memberRepository = memberRepository;
        this.companyBoardRepo = companyBoardRepo;
        this.companyCategoryRepo = companyCategoryRepo;
    }

    @Bean
    public MemberService userService() {
        return new MemberService(memberRepository);
    }

    @Bean
    public CompanyBoardService companyBoardService() {
        return new CompanyBoardService(companyBoardRepo, memberRepository, companyCategoryRepo);
    }

}
