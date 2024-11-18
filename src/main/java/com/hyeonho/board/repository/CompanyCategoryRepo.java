package com.hyeonho.board.repository;

import com.hyeonho.board.domain.company.CompanyCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyCategoryRepo extends JpaRepository<CompanyCategory, Long> {
}
