package com.hyeonho.board.domain.company;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CompanyBoardDTO {
    private String title;
    private String content;
    private int point;
    private Long memberId;
    private Long categoryId;

    // 기본 생성자
    // Jackson이 기본 생성자를 사용한다고 하는데 무슨말인지 모르겠음
    public CompanyBoardDTO() {}

    public CompanyBoardDTO(String title, String content, int point, Long memberId, Long categoryId) {
        this.title = title;
        this.content = content;
        this.point = point;
        this.memberId = memberId;
        this.categoryId = categoryId;
    }
}




