package com.bitcamp.sc.web.comment.dto;

import lombok.Data;

@Data
public class CommentDto {
    private Long idx;
    private long reviewIdx;
    private String writer;
    private String content;



}
