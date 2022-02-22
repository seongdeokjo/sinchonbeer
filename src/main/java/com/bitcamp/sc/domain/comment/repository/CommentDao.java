package com.bitcamp.sc.domain.comment.repository;

import com.bitcamp.sc.domain.comment.domain.Comment;
import com.bitcamp.sc.web.comment.dto.CommentDto;

import java.util.List;

public interface CommentDao {

    void save(CommentDto comment);
    List<Comment> findAll(long reivewIdx);
    int totalCount(long reviewIdx);
}