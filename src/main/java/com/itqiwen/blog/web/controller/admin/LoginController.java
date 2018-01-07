package com.itqiwen.blog.web.controller.admin;

import com.alibaba.fastjson.JSON;
import com.itqiwen.blog.entity.RestResponse;
import com.itqiwen.blog.entity.User;
import com.itqiwen.blog.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class LoginController {

    @Resource
    private LoginService loginService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    @ResponseBody
    public String login(){
        User user = loginService.userLogin(null);
        return JSON.toJSONString(user);
    }
}
