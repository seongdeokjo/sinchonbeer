package com.bitcamp.sc.web.paging;

public class Criteria {

    private int page;  // 보여줄 페이지 번호
    private int perPageNum; // 페이지당 개수

    private String searchType;  // 검색 유형
    private String searchKeyword;   // 검색키워드

    public Criteria() {
        this.page = 1;          // 최초 페이지
        this.perPageNum = 5;    // 페이지당 5개씩
    }

    public int getPage(){
        return page;
    }

    public void setPage(int page){
        if(page <= 0){
            this.page = 1;
            return;
        }
        this.page = page;
    }

    public int getPerPageNum(){
        return perPageNum;
    }

    public void setPerPageNum(int perPageNum){
        if(perPageNum <= 0 || perPageNum > 100){
            this.perPageNum = 5;
            return;
        }
        this.perPageNum = perPageNum;
    }

    public int getStartPage(){
        return (this.page - 1) * perPageNum;
    }

    public String getSearchType(){
        return searchType;
    }

    public void setSearchType(String searchType){
        this.searchType = searchType;
    }

    public String getSearchKeyword(){
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword){
        this.searchKeyword = searchKeyword;
    }

    @Override
    public String toString() {
        return "Criteria{" +
                "page=" + page +
                ", perPageNum=" + perPageNum +
                ", searchType='" + searchType + '\'' +
                ", searchKeyword='" + searchKeyword + '\'' +
                '}';
    }
}
