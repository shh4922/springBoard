package com.hyeonho.board.domain.company;

import com.hyeonho.board.domain.memver.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "company_board") // 회사게시판
public class CompanyBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "companyboard_id")
    private Long companyboard_id;

    @Column(nullable = false, length = 255)
    private String title; // 제목

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content; // 내용

    @Column(nullable = false)
    private int point = 0; // 점수 기본값 0

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_category_id", nullable = false)
    private CompanyCategory category; // 글 카테고리

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = false)
    private Member member; // 작성자

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt; // 생성 시간

    @Column(nullable = false)
    private LocalDateTime updatedAt; // 수정 시간

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}







