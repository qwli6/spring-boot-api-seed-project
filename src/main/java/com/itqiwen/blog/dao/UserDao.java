package com.itqiwen.blog.dao;

import com.itqiwen.blog.entity.UserInfo;
import org.hibernate.criterion.DetachedCriteria;

public interface UserDao extends BaseDao<UserInfo> {

    UserInfo findUserInfoByUid(DetachedCriteria detachedCriteria);
}
