package com.hyeonho.board.domain.board;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 댓글 ID

    private String writer; // 작성자

    @Lob
    private String content; // 댓글 내용

    private LocalDateTime createDate; // 작성 날짜

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post; // 게시글 참조

    @PrePersist
    public void prePersist() {
        this.createDate = LocalDateTime.now();
    }
}