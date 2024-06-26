package com.zone.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zone.dto.ArticleEditDTO;
import com.zone.dto.ArticlePublishDTO;
import com.zone.dto.PageBean;
import com.zone.dto.PageSearchDTO;
import com.zone.entity.Article;
import com.zone.entity.NewArticle;
import com.zone.result.PageResult;
import com.zone.vo.ArticleInfoVO;

import java.util.List;

/**
 * 用户服务
 */

public interface ArticleService extends IService<Article> {


    /**
     * 发布文章
     */
    void publish(ArticlePublishDTO articlePublishDTO);

    /**
     * 编辑文章
     */
    void edit(ArticleEditDTO articleEditDTO);

    /**
     * 删除文章
     * 逻辑删除
     */
    void delete(Integer id);

    /**
     * 根据文章题目进行模糊搜索
     */
    PageResult search(PageSearchDTO pageSearchDTO);

    /**
     * 批量删除文章
     */
    void deleteArticleByIds(List<Integer> ids);

    /**
     * 根据文章ID获取文章详情
     */
    List<NewArticle> listNew();

    /**
     * 文章列表
     */
    PageResult listAllArticle(PageBean pageBean);

    /**
     * 查看文章详情
     */
    ArticleInfoVO checkOutArticle(Integer articleId);

    /**
     * 更新文章点赞数
     */
    void updateArticleLike(Integer articleId);

    /**
     * 查询个人所有文章
     */
    PageResult listPersonalArticle(PageBean pageBean);
}
