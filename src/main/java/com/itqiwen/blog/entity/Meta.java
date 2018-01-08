package com.itqiwen.blog.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 标签，分类，友链实体
 * @author liqiwen
 */
public class Meta {

    private Integer mid;
    private String name;
    private String url;
    private String type;
    private String description;

    //一个标签对应多个文章，一对多
    private Set<Content> contentSet = new HashSet<>();

    public Set<Content> getContentSet() {
        return contentSet;
    }

    public void setContentSet(Set<Content> contentSet) {
        this.contentSet = contentSet;
    }


    //一个分类也对应多篇文章
    private Set<Content> categoryContentSet = new HashSet<>();

    public Set<Content> getCategoryContentSet() {
        return categoryContentSet;
    }

    public void setCategoryContentSet(Set<Content> categoryContentSet) {
        this.categoryContentSet = categoryContentSet;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
