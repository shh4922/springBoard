package com.hyeonho.board.repository;

import com.hyeonho.board.domain.company.CompanyBoard;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<엔티티 명, id 타입>

public interface CompanyBoardRepo extends JpaRepository<CompanyBoard, Long> {

}
