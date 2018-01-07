package com.itqiwen.blog.dao;

import java.util.List;

public interface BaseDao<T> {

    void save(T t);

    void delete(T t);

    void update(T t);

    T findById(String id);

    T findById(Long id);

    List<T> findAll();
}
