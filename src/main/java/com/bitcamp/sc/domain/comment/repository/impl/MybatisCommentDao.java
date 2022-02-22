package com.bitcamp.sc.domain.comment.repository.impl;

import com.bitcamp.sc.domain.comment.domain.Comment;
import com.bitcamp.sc.domain.comment.repository.CommentDao;
import com.bitcamp.sc.web.comment.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MybatisCommentDao implements CommentDao {
    private static final String NAME_SPACE ="CommentMapper";
    private final SqlSessionTemplate template;


    @Override
    public void save(CommentDto comment) {
        template.insert(NAME_SPACE+".save",comment);
    }

    @Override
    public List<Comment> findAll(long reivewIdx) {
        return template.selectList(NAME_SPACE+".findAll",reivewIdx);
    }

    @Override
    public int totalCount(long reviewIdx) {
        return template.selectOne(NAME_SPACE+".totalCommentCount",reviewIdx);
    }
}