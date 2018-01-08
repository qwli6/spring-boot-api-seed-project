package com.itqiwen.blog.service.impl;

import com.itqiwen.blog.dao.ContentDao;
import com.itqiwen.blog.entity.Content;
import com.itqiwen.blog.service.ContentService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service
public class ContentServiceImpl implements ContentService {

    @Resource
    private ContentDao contentDao;

    @Override
    public void saveContent(Content content) {
        contentDao.save(content);
    }

    @Override
    public void deleteContent(String id) {
        Content content = contentDao.findById(id);
        if(content != null){
            contentDao.delete(content);
        }
    }

    @Override
    public void updateContent(Content content) {
        contentDao.update(content);
    }

    @Override
    public List<Content> findAllContents() {
        return contentDao.findAll();
    }

    @Override
    public Content findContentById(String id) {
        return contentDao.findById(id);
    }

    @Override
    public Content findContentByVisitUrl(String visitUrl) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Content.class);
        detachedCriteria.add(Restrictions.eq("visitUrl", visitUrl));
        return contentDao.findContentByVisitUrl(detachedCriteria);
    }
}
