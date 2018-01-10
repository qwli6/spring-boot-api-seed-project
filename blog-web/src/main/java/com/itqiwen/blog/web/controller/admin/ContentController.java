package com.itqiwen.blog.web.controller.admin;

import com.itqiwen.blog.config.Config;
import com.itqiwen.blog.domain.Content;
import com.itqiwen.blog.service.ContentService;
import com.itqiwen.blog.utils.JsonUtil;
import com.itqiwen.blog.web.controller.BaseController;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文章页面控制器
 * @author liqiwen
 */
@RequestMapping("admin")
@RestController
public class ContentController extends BaseController {


    @Resource
    private ContentService contentService;


    /**
     * 获取第一页的 content 内容
     * @param pageCode 当前页码
     * @return
     */
    @RequestMapping(value = "/content/list/{pageCode}", method = RequestMethod.GET)
    public String findContentByPage(@PathVariable("pageCode") String pageCode){
        Integer page = Integer.parseInt(pageCode);
        if(page < 1){
            page = 1;
        }
        Page<Content> pageContent = contentService.findContentByCriteria(page, Config.PAGE_SIZE);
        List<Content> contentList = pageContent.getContent();
        System.out.println("当前数据：" + contentList.size());
        System.out.println("当前页码：" + pageContent.getTotalPages());
        return JsonUtil.toJSONString(contentList);
    }
}
