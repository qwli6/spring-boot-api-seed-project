package com.itqiwen.blog.service.impl;

import com.itqiwen.blog.dao.ArticleRepository;
import com.itqiwen.blog.domain.Article;
import com.itqiwen.blog.domain.Menu;
import com.itqiwen.blog.service.IArticleService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author liqiwen
 */
@Transactional
@Service
public class ArticleServiceImpl implements IArticleService {

    @Resource
    private ArticleRepository articleDao;


    @Override
    public Page<Article> findArticleByCriteria(Integer pageCode, Integer pageSize) {
        //hibernate 页码是从 0开始，这里需要 -1
        Pageable pageable =new PageRequest(pageCode-1, pageSize,
                Sort.Direction.DESC, "createDate");
        Article article = new Article();
        article.setState(Article.STATE_PUBLISH);
        Example<Article> articleExample = Example.of(article);
        return articleDao.findAll(articleExample, pageable);
    }


    @Override
    public Page<Article> findArticleByCriteria(Integer pageCode, Integer pageSize, Menu menu) {
        Pageable pageable = new PageRequest(pageCode - 1, pageSize, Sort.Direction.DESC, "createDate");
        Article content = new Article();
        content.setMenuId(menu.getId());
        Example<Article>  contentExample = Example.of(content);
        return articleDao.findAll(contentExample, pageable);
    }


    @Override
    public Page<Article> findArticleByCriteria(Integer pageCode, Integer pageSize, Integer otherId) {
        Pageable pageable = new PageRequest(pageCode - 1, pageSize, Sort.Direction.DESC, "createDate");
        Article article = new Article();
        Example<Article> example = Example.of(article);
        return articleDao.findAll(example, pageable);
    }


    @Override
    public void saveArticle(Article article) {
        articleDao.save(article);
    }

    @Override
    public Article findArticleById(Integer articleId) {
        return articleDao.findOne(articleId);
    }

    @Override
    public Article findArticleByUrl(String url) {
        return articleDao.findArticleByUrl(url);
    }

    @Override
    public void updateArticle(Article article) {
        articleDao.saveAndFlush(article);
    }


    @Override
    public void deleteArticle(Article article) {
        articleDao.delete(article);
    }

    @Override
    public void deleteByArticleId(Integer articleId) {
        articleDao.delete(articleId);
    }

    @Override
    public void updateRate(Integer articleId, Integer rate) {
        articleDao.updateRate(articleId, rate);
    }

    @Override
    public void updateArchiveId(Integer archiveId, Integer articleId) {
        articleDao.updateArchiveId(archiveId,articleId);
    }

}
