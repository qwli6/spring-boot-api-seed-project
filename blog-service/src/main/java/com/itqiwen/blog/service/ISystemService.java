package com.itqiwen.blog.service;

import com.itqiwen.blog.exception.TipException;


/**
 * @author liqiwen
 */
public interface ISystemService {


    /**
     * 检查用户登录
     * @param username 用户名
     * @param password 密码
     * @return 是否存在该用户
     * @throws TipException 抛出异常信息
     */
    Boolean checkLogin(String username, String password) throws TipException;

    /**
     * 根据用户名查找用户是否存在
     * @param username 用户名
     * @return 是否存在
     * @throws TipException 抛出的异常信息
     */
    Boolean findUserByName(String username) throws TipException;
}
