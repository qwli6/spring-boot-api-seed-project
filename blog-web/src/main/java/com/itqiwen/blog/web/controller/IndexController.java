package com.itqiwen.blog.web.controller;

import com.itqiwen.blog.domain.Content;
import com.itqiwen.blog.service.ContentService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

/**
 * 前台页面控制器
 * @author liqiwen
 */
@Controller
public class IndexController  extends BaseController{

    @Resource
    private ContentService contentService;

    @RequestMapping(value = {"/index","/"}, method = RequestMethod.GET)
    public String mainHome(Model model){

        Page<Content> contentList = contentService.findContentByCriteria(1, 3);
        model.addAttribute("contents", contentList.getContent());

        return "user/index";
    }

}
