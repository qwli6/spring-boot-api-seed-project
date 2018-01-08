package com.itqiwen.blog.service.impl;

import com.itqiwen.blog.dao.MetaDao;
import com.itqiwen.blog.entity.Meta;
import com.itqiwen.blog.service.MetaService;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service
public class MetaServiceImpl implements MetaService {

    private Logger LOGGER = LoggerFactory.getLogger(MetaServiceImpl.class);

    @Resource
    private MetaDao metaDao;


    @Override
    public void saveMeta(Meta meta) {
        LOGGER.debug("enter into saveMeta() method...");
        metaDao.save(meta);
        LOGGER.debug("exit saveMeta() method...");
    }

    @Override
    public void updateMeta(Meta meta) {
        LOGGER.debug("enter into updateMeta() method...");
        metaDao.update(meta);
        LOGGER.debug("exit updateMeta() method...");
    }

    @Override
    public void deleteMeta(Meta meta) {
        LOGGER.debug("enter into deleteMeta() method...");
        metaDao.delete(meta);
        LOGGER.debug("exit deleteMeta() method...");
    }

    @Override
    public List<Meta> findMetaByType(String type) {
        LOGGER.debug("enter into findMetaByType() method...");
        if(!StringUtils.isEmpty(type) && StringUtils.isNotBlank(type)) {
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Meta.class);
            detachedCriteria.add(Restrictions.eq("type", type));
            return metaDao.findMetaByType(detachedCriteria);
        }
        return null;
    }
}
