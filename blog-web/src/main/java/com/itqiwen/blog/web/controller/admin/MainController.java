package com.itqiwen.blog.web.controller.admin;

import com.itqiwen.blog.service.LogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 后台主页面控制器
 * @author liqiwen
 *
 */
@RequestMapping("admin")
@Controller
public class MainController {

    @Resource
    private LogService logService;



    @RequestMapping("/main")
    public String main(){
        return "admin/index";
    }

}
