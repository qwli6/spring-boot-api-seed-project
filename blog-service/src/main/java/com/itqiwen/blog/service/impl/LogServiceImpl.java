package com.itqiwen.blog.service.impl;

import com.itqiwen.blog.dao.LogRepository;
import com.itqiwen.blog.domain.Logs;
import com.itqiwen.blog.service.ILogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liqiwen
 */
@Service
@Transactional
public class LogServiceImpl implements ILogService {

    @Resource
    private LogRepository logRepository;


    @Override
    public void insertLogs(Logs log) {
        logRepository.save(log);
    }

    @Override
    public Page<Logs> findLogByPage(Integer pageCode, Integer pageSize) {
        Pageable pageable = new PageRequest(pageCode - 1 , pageCode, Sort.Direction.DESC,
                "createDt");
        return logRepository.findAll(pageable);
    }
}
