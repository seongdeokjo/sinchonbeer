package com.bitcamp.sc.domain.comment.repository;

import com.bitcamp.sc.domain.comment.domain.Comment;

public interface CommentDao {

    void save(Comment comment);
}