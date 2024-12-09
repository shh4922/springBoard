package com.hyeonho.board.dto.board;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class LikeDTO {

    private Long id; // 좋아요 ID
    private String username; // 좋아요한 사용자 이름
    private LocalDateTime createDate; // 좋아요 날짜
}
