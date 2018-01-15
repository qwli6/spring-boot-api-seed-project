package com.itqiwen.blog.web.controller;

import com.itqiwen.blog.config.Config;
import com.itqiwen.blog.domain.Category;
import com.itqiwen.blog.domain.Content;
import com.itqiwen.blog.domain.Tag;
import com.itqiwen.blog.service.CategoryService;
import com.itqiwen.blog.service.ContentService;
import com.itqiwen.blog.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

    private Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Resource
    private ContentService contentService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private TagService tagService;

    /**
     * 查看首页
     * @param model
     * @return
     */
    @RequestMapping(value = {"/index","/"}, method = RequestMethod.GET)
    public String mainHome(Model model){
        findCategoryList(model);
        findTagList(model);
        Page<Content> contentList = contentService.findContentByCriteria(1, Config.PAGE_SIZE);
        model.addAttribute("contents", contentList.getContent());
        return "user/index";
    }

    /**
     * 获取全部分类
     * @param model
     */
    private void findCategoryList(Model model) {
        List<Category> categoryList = categoryService.findAllCategory();
        model.addAttribute("categoryList", categoryList);
    }

    /**
     * 获取全部标签
     */
    private void findTagList(Model model){
        List<Tag> tagList = tagService.findTagList();
        model.addAttribute("tagList", tagList);
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


    /**
     * 查询分类下面的文章列表
     */
    @RequestMapping("/{url}")
    public String about(@PathVariable("url")String url, Model model){
        findCategoryList(model);
        findTagList(model);
        Category category = categoryService.findCategoryByUrl(url);
        LOGGER.info(category.toString());
        List<Content> contents = contentService.findContentsByCategory(category);
        model.addAttribute("contents", contents);
        return "user/category_content";
    }
}
