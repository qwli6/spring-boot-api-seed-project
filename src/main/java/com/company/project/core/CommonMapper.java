package com.company.project.core;

import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * 描述:
 *  通用 mapper 配置
 *  这是通用 mapper 的接口，不能被扫描到
 * @author liqiwen
 * @date 2018-04-04 09:49
 * 扩展接口，支持批量删除，批量查找，批量增加，以及条件查找
 */
public interface CommonMapper<T> extends IdsMapper<T>,
        InsertListMapper<T>, BaseMapper<T>,
        ConditionMapper<T>{
}
