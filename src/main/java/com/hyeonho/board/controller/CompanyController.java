package com.hyeonho.board.controller;


import com.hyeonho.board.domain.company.CompanyBoard;
import com.hyeonho.board.service.CompanyBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompanyController {

    private final CompanyBoardService companyBoardService;

    @Autowired
    public CompanyController(CompanyBoardService companyBoardService) {
        this.companyBoardService = companyBoardService;
    }

    @GetMapping("/companyboard")
    public List<CompanyBoard> findAll() {
        return  companyBoardService.findAllPost();
    }
}
