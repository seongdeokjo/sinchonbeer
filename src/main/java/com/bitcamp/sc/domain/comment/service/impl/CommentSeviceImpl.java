package com.bitcamp.sc.domain.comment.service.impl;

import com.bitcamp.sc.domain.comment.domain.Comment;
import com.bitcamp.sc.domain.comment.repository.CommentDao;
import com.bitcamp.sc.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentSeviceImpl implements CommentService {

    private final CommentDao commentDao;

    @Override
    public void save(Comment comment) {
        commentDao.save(comment);
    }
}
