package com.zone.service;


import com.zone.dto.CommentDTO;
import com.zone.vo.CommentVO;

import java.util.List;

/**
 * 评论服务
 */
public interface CommentService {


    /**
     * 插入评论
     */
    void insert(CommentDTO commentDTO);

    /**
     * 通过id删除评论
     */
    int deleteById(Integer id);

    /**
     * 通过文章id查询评论
     */
    List<CommentVO> selectByArticleId(Integer articleId);

    /**
     * 通过父id查询评论
     */
    List<CommentVO> selectByParentId(Integer id);
}