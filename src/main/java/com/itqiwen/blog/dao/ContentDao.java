package com.itqiwen.blog.dao;

import com.itqiwen.blog.entity.Content;
import org.hibernate.criterion.DetachedCriteria;

public interface ContentDao extends BaseDao<Content>{

    /**
     * 离线条件查询
     * @param detachedCriteria
     * @return
     */
    Content findContentByVisitUrl(DetachedCriteria detachedCriteria);
}
