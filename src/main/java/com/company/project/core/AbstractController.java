package com.company.project.core;

import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * 描述:
 *
 * @author liqiwen
 * @date 2018-04-09 10:30
 */
public abstract class BaseController {

    /**
     * 获取 session
     * @return
     */
    protected HttpSession getSession(){
        return getRequest().getSession();
    }

    /**
     * 获取 request
     * @return
     */
    protected HttpServletRequest getRequest(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    /**
     * 获取 32 位的 UUID
     * @return
     */
    protected String getUuid(){
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }
}
