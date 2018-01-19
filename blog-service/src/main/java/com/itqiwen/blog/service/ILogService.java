package com.itqiwen.blog.service;

import com.itqiwen.blog.domain.Logs;

import java.util.List;

public interface LogService {

    void insertLogs(Logs log);

    List<Logs> findLogByPage();
}
