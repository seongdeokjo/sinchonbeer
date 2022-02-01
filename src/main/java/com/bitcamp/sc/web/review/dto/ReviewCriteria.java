package com.bitcamp.sc.web.review.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewCriteria {

    private int currentPageNo;  // 현재 페이지
    private int recordsPerPage; // 페이지당 개수
    private int pageSize;   // 페이지 개수
    private String searchType;  // 검색 유형
    private String searchKeyword;   // 검색

    public ReviewCriteria() {
        this.currentPageNo = 1;
        this.recordsPerPage = 10;
        this.pageSize = 10;
    }

    public int getStartPage(){
        return (currentPageNo -1) * recordsPerPage;
    }
}
