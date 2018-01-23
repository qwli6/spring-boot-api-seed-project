package com.itqiwen.blog.service;

import com.itqiwen.blog.domain.ArticleTag;

/**
 * @author liqiwen
 */
public interface IArticleTagService {

    /**
     * 保存 文章 和 Tag 之间的关系
     * @param articleTag articleTag 实体类
     */
    void save(ArticleTag articleTag);
}
