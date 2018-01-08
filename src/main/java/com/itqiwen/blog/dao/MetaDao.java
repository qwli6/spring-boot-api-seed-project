package com.itqiwen.blog.dao;

import com.itqiwen.blog.entity.Meta;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface MetaDao extends BaseDao<Meta> {
    List<Meta> findMetaByType(DetachedCriteria detachedCriteria);
}
