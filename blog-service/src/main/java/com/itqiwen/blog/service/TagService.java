package com.itqiwen.blog.service;

import com.itqiwen.blog.domain.Tag;

import java.util.List;

public interface TagService {

    /**
     * 暂时不暴露用户自主添加标签和分类的方法
     */
    List<Tag> findTagList();

    void saveTag(Tag tag);
}
