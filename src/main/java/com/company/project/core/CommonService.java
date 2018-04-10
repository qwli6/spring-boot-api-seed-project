package com.company.project.core;

import org.apache.ibatis.exceptions.TooManyResultsException;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

/**
 * 描述:
 *  扩展一些基本方法，所有 service 的实现类都必须实现继承该类
 * @author liqiwen
 * @date 2018-04-01 10:00
 */
public interface CommonService<T> {

    void save(T entity);

    void save(List<T> entities);

    void deleteById(Integer id);

    void update(T model);

    T findById(Integer id);

    T findBy(String objectName, Object value) throws TooManyResultsException;

    List<T> findByIds(String ids);

    List<T> findByCondition(Condition condition);

    List<T> findAll();
}
