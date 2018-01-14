package com.itqiwen.blog.web.controller;

import com.itqiwen.blog.config.Config;
import com.itqiwen.blog.domain.Content;
import com.itqiwen.blog.domain.Metas;
import com.itqiwen.blog.service.ContentService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.Set;

/**
 * 前台页面控制器
 * @author liqiwen
 */
@Controller
public class IndexController  extends BaseController{

    @Resource
    private ContentService contentService;

    /**
     * 查看首页
     * @param model
     * @return
     */
    @RequestMapping(value = {"/index","/"}, method = RequestMethod.GET)
    public String mainHome(Model model){

        Page<Content> contentList = contentService.findContentByCriteria(1, Config.PAGE_SIZE);
        model.addAttribute("contents", contentList.getContent());
        Content content = contentList.getContent().get(1);
        Metas metas = content.getMetas();
        System.out.println("关联的分类：" + metas);

        System.out.println("当前文章的标题：" + content.getTitle());

        Set<Metas> tagSet = content.getTagSet();
        System.out.println("tagSet:" + tagSet.size());
        for (Metas meta :
                tagSet) {
            System.out.println("关联的标签： " + meta);
        }

        return "user/index";
    }

    /**
     * 首页文章分页方法
     * @param model
     * @param pageCode
     * @return
     */
    @RequestMapping(value = "/page/{pageCode}", method = RequestMethod.GET)
    public String mainHome(Model model, @PathVariable("pageCode")Integer pageCode){
        if(pageCode > 1) {
            Page<Content> pageContent = contentService.findContentByCriteria(pageCode, Config.PAGE_SIZE);
            model.addAttribute("contents", pageContent.getContent());
        }else{
            return "redirect:/";
        }
        return "user/index";
    }

    /**
     * 根据访问 url 或者 id 去查找Content
     * @param visitUrl
     * @param model
     * @return
     */
    @RequestMapping(value = "article/show/{visitUrl}", method = RequestMethod.GET)
    public String showContent(@PathVariable("visitUrl")String visitUrl, Model model){

        Content content = contentService.findContentByVisitUrl(visitUrl);

        model.addAttribute("content", content);
        return "user/content_detail";
    }

}
