package com.hyeonho.board.domain.board;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // postid

    private String writer; // 작성자

    private String title; // 제목

    @Lob
    private String content; // 내용

    private LocalDateTime createDate; // 작성 날짜

    private LocalDateTime updateDate; // 수정 날짜

    // orphanRemoval = true 는 해당 게시글이 삭제될시, comments들도 모두 삭제되는것같음
    // false 는 반대겠죠잉?
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>(); // 댓글 목록

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likes = new ArrayList<>();


    @PrePersist
    public void prePersist() { // 생성할때
        this.createDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() { // 수정할때
        this.updateDate = LocalDateTime.now();
    }
}