package com.itqiwen.blog.web.controller;


import com.itqiwen.blog.config.Config;
import com.itqiwen.blog.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class BaseController {

    /**
     * 设置标题
     * @param request
     * @param title
     * @return
     */
    public BaseController title(HttpServletRequest request, String title){
        request.setAttribute("title", title);
        return this;
    }

    /**
     * 设置关键字
     * @param request
     * @param keywords
     * @return
     */
    public BaseController keywords(HttpServletRequest request, String keywords){
        request.setAttribute("keywords", keywords);
        return this;
    }

    /**
     * 获取请求绑定登录的对象
     */
    public User user(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(null == session){
            return null;
        }
        return (User) session.getAttribute(Config.LOGIN_SESSION_KEY);
    }

    public String getUid(HttpServletRequest request){
        return this.user(request).getUid();
    }

    /**
     * 渲染视图
     */
    public String rederView(String viewName){
        return viewName;
    }

    /**
     * 渲染错误视图
     */
    public String rederError(String viewName){
        return "common/" + viewName;
    }

}
