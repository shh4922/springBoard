package com.hyeonho.board.dto.board;

import com.hyeonho.board.domain.board.Comment;
import com.hyeonho.board.domain.board.Like;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PostResponseDTO {
    private Long id; // postid
    private String writer; // 작성자
    private String title; // 제목
    private String content; // 내용
    private LocalDateTime createDate; // 작성 날짜
    private LocalDateTime updateDate; // 수정 날짜
    private List<CommentDTO> comments; // 댓글 목록
    private List<LikeDTO> likes;

}
