package com.itqiwen.blog.service;

import com.itqiwen.blog.entity.Content;

import java.util.List;

public interface ContentService {

    void saveContent(Content content);

    void deleteContent(String id);

    void updateContent(Content content);

    List<Content> findAllContents();

    Content findContentById(String id);

    Content findContentByVisitUrl(String visitUrl);


}
