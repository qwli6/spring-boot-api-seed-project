package com.itqiwen.blog.service;

import com.itqiwen.blog.domain.Link;

import java.util.List;

public interface LinkService {

    void saveLink(Link link);

    Link findLinkById(Integer id);

    List<Link> findAllLinks();

    void deleteLink(Link link);

    void deleteLink(Integer id);

    /**
     * 更新链接没必要，链接字段少，如果错了，直接删除重新添加即可
     * @param link
     */
    @Deprecated
    void updateLink(Link link);

}
