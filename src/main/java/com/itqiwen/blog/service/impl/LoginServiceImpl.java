package com.itqiwen.blog.service.impl;

import com.itqiwen.blog.dao.LoginDao;
import com.itqiwen.blog.entity.User;
import com.itqiwen.blog.service.LoginService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    @Resource
    private LoginDao userDao;

    @Override
    public User userLogin(User user) {
        List<User> userList = userDao.findAll();
        if(userList != null && userList.size()>0){
            return userList.get(0);
        }
        return null;
    }
}
