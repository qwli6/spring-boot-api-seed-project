package com.itqiwen.blog.dao.impl;

import com.itqiwen.blog.dao.BaseDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

@SuppressWarnings("all")
public class BaseDaoImpl<T> implements BaseDao<T> {

    private Class aClass;

    @Resource
    private SessionFactory sessionFactory;

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }


    public BaseDaoImpl() {
        Class c = this.getClass();
        Type type = c.getGenericSuperclass();

        if(type instanceof ParameterizedType){
            ParameterizedType p = (ParameterizedType) type;
            Type[] typeArguments = p.getActualTypeArguments();
            this.aClass = (Class) typeArguments[0];
        }
    }

    @Override
    public void save(T t) {
        this.getSession().save(t);
    }

    @Override
    public void delete(T t) {
        this.getSession().delete(t);
    }

    @Override
    public void update(T t) {
        this.getSession().update(t);
    }

    @Override
    public T findById(String id) {
        return this.getSession().get((Class<T>) aClass, id);
    }

    @Override
    public T findById(Long id) {
        return this.getSession().get((Class<T>) aClass, id);
    }

    @Override
    public List<T> findAll() {
        Query query = this.getSession().createQuery("from " + aClass.getSimpleName());
        return (List<T>) query.list();
    }
}
