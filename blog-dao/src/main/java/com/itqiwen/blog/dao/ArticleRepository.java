package com.itqiwen.blog.dao;

import com.itqiwen.blog.domain.Article;
import com.itqiwen.blog.domain.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends JpaRepository<Content, Integer> {

    /**
     * 根据内容状态和 type 类型来查询文章列表
     * @param state
     * @param type
     * @return
     */
//    List<Content> findContentsByStateAndType(@Param("state")String state, @Param("type")String type);

    /**
     * 根据 content url 查找文章
     * @param visitUrl
     * @return
     */
//    Content findContentByVisitUrl(@Param("visitUrl")String visitUrl);


    /**
     * 根据文章 id 查找文章
     */
    Content findContentByContentId(@Param("contentId")Integer contentId);

    /**
     * 根据分类查找文章
     * @param category
     * @return
     */
//    List<Content> findContentsByCategory(@Param("category")Menu category);

    /**
     * 修改文章点击量
     * @param cid
     * @param visitCount
     */
    @Modifying
    @Query("update Content set visitCount =?1 where cid =?2")
    void updateVisitCount(@Param("cid") Integer cid, @Param("visitCount") int visitCount);


}
