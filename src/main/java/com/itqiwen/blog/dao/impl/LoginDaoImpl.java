package com.itqiwen.blog.dao.impl;

import com.itqiwen.blog.dao.BaseDao;
import com.itqiwen.blog.dao.LoginDao;
import com.itqiwen.blog.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoginDaoImpl extends BaseDaoImpl<User> implements LoginDao {


    @Override
    public List<User> findUserByUserNameAndPwd(User user) {
//        Query fromUser = this.getSession().createQuery("from User");
//        List list = fromUser.list();
        return null;
    }
}
