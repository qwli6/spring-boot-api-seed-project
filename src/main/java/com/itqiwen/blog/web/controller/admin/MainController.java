package com.itqiwen.blog.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台首页控制器
 */
@Controller
@RequestMapping("admin")
public class MainController {


    /**
     * 首页
     * @return
     */
    @RequestMapping("/main")
    public String main(){


        return null;
    }
}
