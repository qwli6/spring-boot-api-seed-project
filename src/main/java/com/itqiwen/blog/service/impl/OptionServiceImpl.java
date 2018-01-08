package com.itqiwen.blog.service.impl;

import com.itqiwen.blog.dao.OptionDao;
import com.itqiwen.blog.entity.Options;
import com.itqiwen.blog.service.OptionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service
public class OptionServiceImpl implements OptionService {

    @Resource
    private OptionDao optionDao;

    @Override
    public List<Options> findAllOption() {
        return optionDao.findAll();
    }

    @Override
    public void updateOptions(Options options) {
        optionDao.update(options);
    }

    @Override
    public void saveOptions(Options options) {
        optionDao.save(options);
    }

    @Override
    public Options findOptionsById(Long optionsId) {
        return optionDao.findById(optionsId);
    }

    @Override
    public void deleteOptions(Options options) {
        optionDao.delete(options);
    }
}
