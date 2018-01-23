package com.itqiwen.blog.service;

import com.itqiwen.blog.domain.Tag;

import java.util.List;

/**
 * 标签 service
 * TODO 暂时不暴露用户自主添加 tag 的方法
 * @author liqiwen
 */
public interface ITagService {


    /**
     * 查找标签列表
     * @return 标签列表
     */
    List<Tag> findTagList();

    /**
     * 保存Tag
     * @param tag 要保存的 tag 对象
     */
    void saveTag(Tag tag);

    /**
     * 根据 urlName 查找 Tag
     * @param urlName  tag urlName
     * @return Tag 对象
     */
    Tag findTagByUrlName(String urlName);

    /**
     * 更新 tag
     * @param tag 要更新的 tag 对象
     */
    void updateTag(Tag tag);
}
