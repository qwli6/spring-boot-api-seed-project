package com.itqiwen.blog.service.impl;

import com.itqiwen.blog.dao.UserRepository;
import com.itqiwen.blog.domain.User;
import com.itqiwen.blog.exception.TipException;
import com.itqiwen.blog.service.UserService;
import com.itqiwen.blog.utils.TextUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.swing.plaf.TextUI;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;


    /**
     * 根据用户名和密码查询用户，再根据查询出来的用户
     * 的状态判断用户是否有效
     * @param user
     * @return
     */
    @Override
    public User findUserByNameAndPwd(User user) {
        return null;
//        return userRepository.findUserByUsernameAndPassword(user);
    }

    @Override
    public User login(String username, String password) throws TipException {
        User user = null;
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            throw new TipException("用户名或者密码不能为空");
        }
        user = userRepository.findUserByUsername(username);
        if(user == null){
            throw new TipException("该用户不存在");
        } else {
            password = TextUtils.MD5encode(username + password);
            user = userRepository.findUserByUsernameAndPassword(username, password);
            if(user.getState().equals("1")){
                throw new TipException("用户未激活，联系管理员激活用户或者重新注册");
            }
        }
        System.out.println("用户是否存在：" + user.getNickname() + "==" + user.getPassword());
        return user;
    }
}
