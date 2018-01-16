package com.itqiwen.blog.service;

import com.itqiwen.blog.domain.User;
import com.itqiwen.blog.exception.TipException;

public interface UserService {

    User findUserByNameAndPwd(User user);

    User login(String username, String password) throws TipException;
}
