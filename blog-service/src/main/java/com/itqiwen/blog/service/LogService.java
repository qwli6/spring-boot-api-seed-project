package com.itqiwen.blog.service;

import com.itqiwen.blog.domain.Log;

import java.util.List;

public interface LogService {

    void insertLogs(Log log);

    List<Log> findLogByPage();
}
