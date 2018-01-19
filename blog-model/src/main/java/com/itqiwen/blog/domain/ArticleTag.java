package com.itqiwen.blog.domain;


import javax.persistence.*;
import java.io.Serializable;

/**
 * 保存文章和标签之间的关系
 */
@Entity
@Table(name = "sblog_tag_article", catalog = "simple_blog")
public class ArticleTag implements Serializable{

    private Integer id;
    private Short articleId;
    private Short tagId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "article_id")
    public Short getArticleId() {
        return articleId;
    }

    public void setArticleId(Short articleId) {
        this.articleId = articleId;
    }

    @Column(name = "tag_id")
    public Short getTagId() {
        return tagId;
    }

    public void setTagId(Short tagId) {
        this.tagId = tagId;
    }
}
