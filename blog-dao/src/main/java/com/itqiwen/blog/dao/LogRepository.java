package com.itqiwen.blog.dao;

import com.itqiwen.blog.domain.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 日志
 * @author liqiwen
 */
@Repository
public interface LogRepository extends JpaRepository<Log, Integer> {

}
