package com.itqiwen.blog.service;

import com.itqiwen.blog.domain.Category;
import com.itqiwen.blog.domain.Content;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ContentService {
    List<Content> findContentByPage(int pageCode, Integer pageSize);

    /**
     * 待条件分页
     * @param pageCode
     * @param pageSize
     * @return
     */
    Page<Content> findContentByCriteria(Integer pageCode, Integer pageSize);


    /**
     * 带条件分页
     * @param pageCode  //当前页码
     * @param pageSize  //当前页码大小
     * @param category  //当前分类
     * @return
     */
    Page<Content> findContentByCriteria(Integer pageCode, Integer pageSize, Category category);

    /**
     * 保存文章
     * @param content
     */
    void saveContent(Content content);

    /**
     * 根据文章 id 查找文章
     * @param cid
     * @return
     */
    Content findContentById(String cid);

    /**
     * 根据文章访问 url 查找文章
     * @param url
     * @return
     */
    Content findContentByVisitUrl(String url);

    /**
     * 更新文章
     * @param content
     */
    void updateContent(Content content);

    List<Content> findContentsByCategory(Category category);

    void deleteContent(Content content);

    /**
     * 更改文章点击数量
     * @param cid
     * @param visitCount
     */
    void updateVisitCount(Integer cid, int visitCount);

    List<Content> findContentByCriteria(Category category);
}
