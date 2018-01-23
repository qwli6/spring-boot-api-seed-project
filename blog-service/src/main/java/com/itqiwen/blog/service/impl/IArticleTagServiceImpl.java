package com.itqiwen.blog.service.impl;

import com.itqiwen.blog.dao.ArticleTagRepository;
import com.itqiwen.blog.domain.ArticleTag;
import com.itqiwen.blog.service.IArticleTagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author liqiwen
 */
@Transactional
@Service
public class IArticleTagServiceImpl implements IArticleTagService {

    @Resource
    private ArticleTagRepository articleTagRepository;

    @Override
    public void save(ArticleTag articleTag) {
        articleTagRepository.save(articleTag);
    }
}
