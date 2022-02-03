package com.bitcamp.sc.web.paging;

public class Criteria {

    private int page;  // 보여줄 페이지 번호
    private int perPageNum; // 페이지당 개수

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

    @Override
    public String toString() {
        return "Criteria{" +
                "page=" + page +
                ", perPageNum=" + perPageNum +
                '}';
    }
}
