package com.itqiwen.blog.dao.impl;

import com.itqiwen.blog.dao.UserDao;
import com.itqiwen.blog.entity.UserInfo;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends BaseDaoImpl<UserInfo> implements UserDao {

    @Override
    public UserInfo findUserInfoByUid(DetachedCriteria detachedCriteria) {
        Criteria executableCriteria = detachedCriteria.getExecutableCriteria(getSession());
        UserInfo userInfo = (UserInfo) executableCriteria.list().get(0);
        return userInfo;
    }
}
