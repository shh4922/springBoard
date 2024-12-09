package com.hyeonho.board.dto.board;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDTO {

    private String writer;
    private String title;
    private String content;
}

