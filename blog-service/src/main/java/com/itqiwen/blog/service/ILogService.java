package com.itqiwen.blog.service;

import com.itqiwen.blog.domain.Logs;
import org.springframework.data.domain.Page;


/**
 * @author liqiwen
 */
public interface ILogService {

    /**
     * 插入操作日志
     * @param log 日志实体
     */
    void insertLogs(Logs log);

    /**
     * 分页查找日志信息
     * @param pageCode 当前页
     * @param pageSize 当前页大小
     * @return 当前页中的日志集合
     */
    Page<Logs> findLogByPage(Integer pageCode, Integer pageSize);
}
