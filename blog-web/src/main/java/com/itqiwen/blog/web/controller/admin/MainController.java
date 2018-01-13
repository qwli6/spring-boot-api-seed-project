package com.itqiwen.blog.web.controller.admin;

import com.itqiwen.blog.domain.Content;
import com.itqiwen.blog.service.ContentService;
import com.itqiwen.blog.service.LogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @Resource
    private ContentService contentService;


    @RequestMapping("/main")
    public String main(){
        return "admin/index";
    }

    @RequestMapping(value = "/content/{pageCode}", method = RequestMethod.GET)
    public String findContentList(@PathVariable("pageCode")Integer pageCode,
                                  HttpServletRequest request){


        System.out.println("admin 分页查找数据");
        List<Content> page = contentService.findContentByPage(pageCode, 4);
        request.setAttribute("page", page);

        return "admin/index";
    }

}
