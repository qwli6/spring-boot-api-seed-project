package com.itqiwen.blog.service.impl;

import com.itqiwen.blog.dao.UserDao;
import com.itqiwen.blog.entity.UserInfo;
import com.itqiwen.blog.service.UserService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public UserInfo findUserInfoById(DetachedCriteria detachedCriteria) {
        return userDao.findUserInfoByUid(detachedCriteria);
    }
}
