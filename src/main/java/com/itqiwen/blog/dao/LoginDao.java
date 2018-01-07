package com.itqiwen.blog.dao;

import com.itqiwen.blog.entity.User;

import java.util.List;

public interface LoginDao extends BaseDao<User> {
    /**
     * 根据用户名和密码查询用户
     * @param user
     * @return
     */
    List<User> findUserByUserNameAndPwd(User user);
}
