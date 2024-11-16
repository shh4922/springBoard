package com.hyeonho.board.domain.company;

import jakarta.persistence.*;

@Entity
public class CompanyCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_category_id")
    private Long company_category_id;

    @Column(nullable = false)
    private String category1;
}
