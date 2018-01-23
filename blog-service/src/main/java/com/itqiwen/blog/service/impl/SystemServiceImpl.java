package com.itqiwen.blog.service.impl;

import com.itqiwen.blog.dao.SystemRepository;
import com.itqiwen.blog.domain.BlogSystem;
import com.itqiwen.blog.exception.TipException;
import com.itqiwen.blog.service.ISystemService;
import com.itqiwen.blog.utils.TextUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liqiwen
 */
@Service
@Transactional(rollbackFor = TipException.class)
public class SystemServiceImpl implements ISystemService {

    @Resource
    private SystemRepository systemRepository;


    @Override
    public Boolean checkLogin(String username, String password) throws TipException {
        password = TextUtils.md5Encode(username+password);
        List<BlogSystem> blogSystems = systemRepository.checkLogin(username, password);
        if(blogSystems.size() == 2) {
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Boolean findUserByName(String username) throws TipException {
        if(StringUtils.isEmpty(username)){
            throw new TipException("用户名不能为空");
        }
        List<BlogSystem> blogSystems = systemRepository.findUserByName(username);
        if(blogSystems.size() > 0) {
            return true;
        }else{
            return false;
        }
    }
}
