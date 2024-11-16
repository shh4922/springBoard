package com.hyeonho.board.service;

import com.hyeonho.board.domain.company.CompanyBoard;
import com.hyeonho.board.repository.CompanyBoardRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CompanyBoardService {

    private final CompanyBoardRepo companyBoardRepo;

    @Autowired
    public CompanyBoardService(CompanyBoardRepo companyBoardRepo) {
        this.companyBoardRepo = companyBoardRepo;
    }

    public List<CompanyBoard> findAllPost() {
        return companyBoardRepo.findAll();
    }
}
