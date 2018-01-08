package com.itqiwen.blog.dao.impl;

import com.itqiwen.blog.dao.ContentDao;
import com.itqiwen.blog.entity.Content;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContentDaoImpl extends BaseDaoImpl<Content> implements ContentDao {

    @Override
    public Content findContentByVisitUrl(DetachedCriteria detachedCriteria) {
        Criteria criteria = detachedCriteria.getExecutableCriteria(this.getSession());
        List<Content> list = criteria.list();
        return list.get(0);
    }
}
