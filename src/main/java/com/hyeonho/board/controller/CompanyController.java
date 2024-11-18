package com.hyeonho.board.controller;


import com.hyeonho.board.domain.Member;
import com.hyeonho.board.domain.company.CompanyBoard;
import com.hyeonho.board.domain.company.CompanyBoardDTO;
import com.hyeonho.board.service.CompanyBoardService;
import com.hyeonho.board.util.DefaultRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companyboard")
public class CompanyController {

    private final CompanyBoardService companyBoardService;

    @Autowired
    public CompanyController(CompanyBoardService companyBoardService) {
        this.companyBoardService = companyBoardService;
    }

    @GetMapping("/")
    public List<CompanyBoard> findAll() {
        return  companyBoardService.findAllPost();
    }

    @PostMapping("/create/reviewpost")
    public ResponseEntity<Void> createPost(@RequestBody CompanyBoardDTO companyBoardDTO) {
        return new ResponseEntity(DefaultRes.res(200,"글작성 성공",companyBoardDTO),HttpStatus.OK);
    }
}
