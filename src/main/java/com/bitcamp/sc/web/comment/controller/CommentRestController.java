package com.bitcamp.sc.web.comment.controller;

import com.bitcamp.sc.domain.comment.domain.Comment;
import com.bitcamp.sc.domain.comment.service.CommentService;
import com.bitcamp.sc.web.comment.dto.CommentDto;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CommentRestController {
    private final CommentService commentService;

    @RequestMapping(value = { "/comments", "/comments/{idx}" }, method = { RequestMethod.POST, RequestMethod.PATCH })
    public JsonObject registerComment(@PathVariable(value = "idx", required = false) Long idx, @RequestBody final CommentDto params) {
        JsonObject jsonObj = new JsonObject();

        try {
            if (idx != null) {
                params.setIdx(idx);
            }

            boolean isRegistered = commentService.save(params);
            jsonObj.addProperty("result", isRegistered);

        } catch (DataAccessException e) {
            jsonObj.addProperty("message", "데이터베이스 처리 과정에 문제가 발생하였습니다.");

        } catch (Exception e) {
            jsonObj.addProperty("message", "시스템에 문제가 발생하였습니다.");
        }

        return jsonObj;
    }

    @GetMapping(value = "/comments/{reviewIdx}")
    public JsonObject getCommentList(@PathVariable("reviewIdx") Long reviewIdx, @ModelAttribute("params") CommentDto params) {
        JsonObject jsonObj = new JsonObject();
        List<Comment> commentList = commentService.findAll(reviewIdx);
        log.info("commentList = {}",commentList);
        if (CollectionUtils.isEmpty(commentList) == false) {
            JsonArray jsonArr = new Gson().toJsonTree(commentList).getAsJsonArray();
            jsonObj.add("commentList", jsonArr);
            log.info("jsonArr = {}",jsonArr);
            log.info("jsonObj = {}",jsonObj);
        }
        return jsonObj;
    }

    @GetMapping("/comments/totalCount/{reviewIdx}")
    public int getTotalCount(@PathVariable("reviewIdx") Long reviewIdx){
        return commentService.totalCount(reviewIdx);
    }
}