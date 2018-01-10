package com.itqiwen.blog.dao;

import com.itqiwen.blog.domain.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content, Integer> {


    /**
     * 根据内容状态和 type 类型来查询文章列表
     * @param state
     * @param type
     * @return
     */
    List<Content> findContentsByStateAndType(@Param("state")String state, @Param("type")String type);

    /**
     * 根据 content url 查找文章
     * @param visitUrl
     * @return
     */
    Content findContentByVisitUrl(@Param("visitUrl")String visitUrl);

    void updateContent(Content content);
}
