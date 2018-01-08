package com.itqiwen.blog.web.controller;

import com.itqiwen.blog.service.MetaService;
import com.itqiwen.blog.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.annotation.Resource;

@Controller
public class IndexController {

    @Resource
    private MetaService metaService;

    @Resource
    private UserService userService;



    /**
     * 访问用户前台首页
     * @return
     */
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(){

        return null;
    }
}
