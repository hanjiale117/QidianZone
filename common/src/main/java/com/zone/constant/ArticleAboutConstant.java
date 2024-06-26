package com.zone.constant;

import java.time.LocalDateTime;

public class ArticleAboutConstant {
    // 默认点赞数
    public static final Integer DEFAULT_ARTICLE_LIKE_VALUE = 0;
    // 默认评论数
    public static final Integer DEFAULT_ARTICLE_COMMENT_COUNT = 0;
    // 默认浏览数
    public static final Integer DEFAULT_ARTICLE_VIEW = 0;
    // 默认文章状态
    public static final Integer DEFAULT_STATUS = 1;
    // 默认创建者
    public static final String DEFAULT_CREATOR = "admin";
    // 默认访问权限
    public static final Integer DEFAULT_ARTICLE_VIEW_POWER = 1;
    // 默认删除时间 1970-01-01 00:00:00
    public static final LocalDateTime DEFAULT_ARTICLE_DELETE_TIME =
            LocalDateTime.of(1970, 1, 1, 0, 0, 0);


}
