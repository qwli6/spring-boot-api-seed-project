package com.itqiwen.blog.service;

import com.itqiwen.blog.entity.UserInfo;
import org.hibernate.criterion.DetachedCriteria;

public interface UserService {

    UserInfo findUserInfoById(DetachedCriteria detachedCriteria);
}
