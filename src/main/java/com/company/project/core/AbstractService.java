package com.company.project.core;

import org.apache.ibatis.exceptions.TooManyResultsException;
import tk.mybatis.mapper.entity.Condition;
import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * 描述:
 *  所有 Service 的父类，实现 CommonService 中的公共方法，
 *  自定义的 Service 接口中只实现特殊的逻辑方法
 * @author liqiwen
 * @date 2018-03-08 10:06
 */
public abstract class AbstractService<T> implements CommonService<T> {

    @Resource
    private CommonMapper<T> mapper;

    private Class<T> modelClass;

    public AbstractService() {
        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
        //通过反射获取到泛型 T 的类型
        modelClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
    }

    public void save(T model){
        mapper.insertSelective(model);
    }

    public void save(List<T> models){
        mapper.insertList(models);
    }

    public void deleteById(Integer id){
        mapper.deleteByPrimaryKey(id);
    }

    public void update(T model){
        mapper.updateByPrimaryKeySelective(model);
    }

    public T findById(Integer id){
       return mapper.selectByPrimaryKey(id);
    }

    //通过反射获取对象的属性名称，属性值
    public T findBy(String objectName, Object value) throws TooManyResultsException{
        return null;
    }

    public List<T> findByIds(String ids){
        return mapper.selectByIds(ids);
    }

    public List<T> findByCondition(Condition condition){
        return mapper.selectByCondition(condition);
    }

    public List<T> findAll(){
        return mapper.selectAll();
    }
}
