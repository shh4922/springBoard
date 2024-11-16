package com.hyeonho.board;

import com.hyeonho.board.domain.company.CompanyBoard;
import com.hyeonho.board.repository.CompanyBoardRepo;
import com.hyeonho.board.service.CompanyBoardService;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Transactional
public class CompanyBoardTest {

    @Autowired
    CompanyBoardService companyBoardService;

    @Autowired
    CompanyBoardRepo companyBoardRepo;

    @Test
    public void 게시판전체조회() {
        List<CompanyBoard> boards = companyBoardService.findAllPost();
    }
}
