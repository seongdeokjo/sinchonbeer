package com.bitcamp.sc.domain.comment.service.impl;

import com.bitcamp.sc.domain.comment.domain.Comment;
import com.bitcamp.sc.domain.comment.repository.CommentDao;
import com.bitcamp.sc.domain.comment.service.CommentService;
import com.bitcamp.sc.web.comment.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentSeviceImpl implements CommentService {

    private final CommentDao commentDao;

    @Override
    public boolean save(CommentDto comment) {
        int queryResult = 0;
        if (comment.getIdx() == null) {
            queryResult = commentDao.save(comment);
        } else {
            queryResult = commentDao.update(comment);
        }
        return (queryResult == 1) ? true : false;
    }

    @Override
    public boolean delete(Long idx) {
        int queryResult = 0;
        if(idx != null){
            queryResult = commentDao.delete(idx);
        }
        return (queryResult == 1) ? true : false;
    }

    @Override
    public List<Comment> findAll(long reviewIdx) {
        return commentDao.findAll(reviewIdx);
    }

    @Override
    public int totalCount(long reviewIdx) {
        return commentDao.totalCount(reviewIdx);
    }
}
