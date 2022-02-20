package com.bitcamp.sc.domain.comment.repository.impl;

import com.bitcamp.sc.domain.comment.domain.Comment;
import com.bitcamp.sc.domain.comment.repository.CommentDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MybatisCommentDao implements CommentDao {
    private static final String NAME_SPACE ="CommentMapper";
    private final SqlSessionTemplate template;


    @Override
    public void save(Comment comment) {
        template.insert(NAME_SPACE+".save",comment);
    }
}