package com.itqiwen.blog.service.impl;

import com.itqiwen.blog.dao.LogRepository;
import com.itqiwen.blog.domain.Log;
import com.itqiwen.blog.service.LogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class LogServiceImpl implements LogService {

    @Resource
    private LogRepository logRepository;

    @Override
    public void insertLogs(Log log) {
        logRepository.save(log);
    }

    /**
     * 默认查看最新的20条日志
     * @return
     */
    @Override
    public List<Log> findLogByPage() {
        Pageable pageable = new PageRequest(0, 20, Sort.Direction.DESC,
                "createDt");
        Page<Log> logPage = logRepository.findAll(pageable);
        return logPage.getContent();
    }
}
