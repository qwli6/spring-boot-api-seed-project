package com.itqiwen.blog.web.controller;


import com.alibaba.fastjson.JSON;
import com.itqiwen.blog.entity.UserInfo;
import com.itqiwen.blog.service.UserService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class UserController {

    @Resource
    private UserService userService;


    @RequestMapping("/user/info")
    @ResponseBody
    public String getUserInfo(){
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UserInfo.class);
        detachedCriteria.add(Restrictions.eq("uid", "7662671af38711e7b068bfe22f45cc30"));
        UserInfo userInfo = userService.findUserInfoById(detachedCriteria);
        return JSON.toJSONString(userInfo);
    }


}
