package com.itqiwen.blog.service;

import com.itqiwen.blog.domain.Article;
import com.itqiwen.blog.domain.Menu;
import org.springframework.data.domain.Page;

/**
 * @author liqiwen
 */
public interface IArticleService {

    /**
     * 分页查找文章对象
     * @param pageCode 当前页码
     * @param pageSize 查询页大小
     * @return Article Page 对象
     */
    Page<Article> findArticleByCriteria(Integer pageCode, Integer pageSize);

    /**
     * 分页查找对象
     * @param pageCode 当前页码
     * @param pageSize 查询页大小
     * @param menu  菜单分类
     * @return Article Page 对象
     */
    Page<Article> findArticleByCriteria(Integer pageCode, Integer pageSize, Menu menu);


    /**
     * 分页查找对象
     * @param pageCode 当前页码
     * @param pageSize 查询页大小
     * @param otherId  id
     * @return Article Page 对象
     */
    Page<Article> findArticleByCriteria(Integer pageCode, Integer pageSize, Integer otherId);

    /**
     * 保存文章
     * @param article 要保存的文章
     */
    void saveArticle(Article article);

    /**
     * 更新文章
     * @param article 要更新的文章
     */
    void updateArticle(Article article);

    /**
     * 根据文章 id（主键） 查找文章实体
     * @param articleId  文章id
     * @return 文章实体
     */
    Article findArticleById(Integer articleId);

    /**
     * 根据文章访问 url 查找文章
     * @param url 访问 url
     * @return 返回文章实体类
     */
    Article findArticleByUrl(String url);

    /**
     * 删除文章
     * @param article 要删除的文章实体
     */
    void deleteArticle(Article article);


    /**
     * 根据文章主键删除文章
     * @param articleId   文章主键
     */
    void deleteByArticleId(Integer articleId);

    /**
     * 根据文章主键更改文章点击量
     * @param articleId 文章 id
     * @param rate 要更改的点击量
     */
    void updateRate(Integer articleId, Integer rate);

    /**
     * 更改文章的归档
     * @param archiveId  文章归档 id
     * @param articleId  文章 id
     */
    void updateArchiveId(Integer archiveId, Integer articleId);
}
