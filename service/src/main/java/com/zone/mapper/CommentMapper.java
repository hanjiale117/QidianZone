package com.zone.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zone.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper extends BaseMapper<Comment>{
    /**
     * 通过文章id查询评论
     */

    List<Comment> selectByArticleId(@Param("articleId")Integer articleId);


    /**
     * 通过父id查询评论
     */
    List<Comment> selectByParentId(@Param("id") Integer id);

    /**
     * 通过子id查询评论
     */
    List<Comment> selectBySonId(Integer sonId);
}