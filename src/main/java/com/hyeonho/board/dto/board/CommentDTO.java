package com.hyeonho.board.dto.board;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CommentDTO {

    private Long id; // 댓글 ID
    private String writer; // 작성자
    private String content; // 댓글 내용
    private LocalDateTime createDate; // 작성 날짜
}