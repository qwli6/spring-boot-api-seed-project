package com.itqiwen.blog.dao.impl;

import com.itqiwen.blog.dao.MetaDao;
import com.itqiwen.blog.entity.Meta;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import java.util.List;

@SuppressWarnings("all")
@Repository
public class MetaDaoImpl extends BaseDaoImpl<Meta> implements MetaDao {

    @Override
    public List<Meta> findMetaByType(DetachedCriteria detachedCriteria) {
        Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
        return criteria.list();
    }
}
