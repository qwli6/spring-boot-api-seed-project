package com.itqiwen.blog.service;

import com.itqiwen.blog.domain.Archive;

import java.util.List;

/**
 * @author liqiwen
 */
public interface IArchiveService {

    /**
     * 查找归档列表
     * @return 归档 List
     */
    List<Archive> findArchiveList();

    /**
     * 根据 Title 查找 Archive
     * @param title 标题
     * @return Archive 实体类
     */
    Archive findArchiveByTitle(String title);

    /**
     * 保存归档信息
     * @param newArchive 新归档
     * @return 获取保存后的 Archive 实体类
     */
    Archive saveArchive(Archive newArchive);

    /**
     * 更新归档中的 count
     * @param archive archive 实体类作为参数
     */
    void updateCount(Archive archive);

    /**
     * 根据 urlName 查找 Archive
     * @param urlName Archive urlName
     * @return Archive 实体类
     */
    Archive findArchiveByUrlName(String urlName);
}
