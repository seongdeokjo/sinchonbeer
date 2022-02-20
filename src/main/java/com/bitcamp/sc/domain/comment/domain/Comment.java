package com.bitcamp.sc.domain.comment.domain;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class Comment {
    private long idx;
    private long reviewIdx;
    private String writer;
    private String content;
    private Timestamp insertTime;
}
