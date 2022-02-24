package com.bitcamp.sc.domain.comment.service;

import com.bitcamp.sc.domain.comment.domain.Comment;
import com.bitcamp.sc.web.comment.dto.CommentDto;

import java.util.List;

public interface CommentService {
    boolean save(CommentDto comment);
    boolean delete(Long idx);
    List<Comment> findAll(long reviewIdx);
    int totalCount(long reviewIdx);
}
