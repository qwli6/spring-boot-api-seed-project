package com.itqiwen.blog.web.controller;

import com.itqiwen.blog.config.CategoryType;
import com.itqiwen.blog.config.Config;
import com.itqiwen.blog.domain.Category;
import com.itqiwen.blog.domain.Content;
import com.itqiwen.blog.domain.Tag;
import com.itqiwen.blog.service.CategoryService;
import com.itqiwen.blog.service.ContentService;
import com.itqiwen.blog.service.TagService;
import com.itqiwen.blog.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.*;

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
        Category category = new Category();
        category.setId(1); //查询 index 首页展示的文章
        Page<Content> contentList = contentService.findContentByCriteria(1,
                Config.PAGE_SIZE, category);
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
            Category category = new Category();
            category.setId(1);
            Page<Content> pageContent = contentService.findContentByCriteria(pageCode,
                    Config.PAGE_SIZE, category);
            model.addAttribute("contents", pageContent.getContent());
        }else{
            return "redirect:/";
        }
        return "user/index";
    }

    /**
     * 根据访问 url 或者 id 去查找Content，
     * 查看文章详情
     * @param visitUrl 访问 url
     * @param model
     * @return
     */
    @RequestMapping(value = "article/show/{visitUrl}", method = RequestMethod.GET)
    public String showContent(@PathVariable("visitUrl")String visitUrl, Model model){
        Content content = contentService.findContentByVisitUrl(visitUrl);
        /**
         * 更改文章点击量
         */
        int visitCount = content.getVisitCount();
        visitCount ++;
        content.setVisitCount(visitCount);
        contentService.updateVisitCount(content.getCid(), visitCount);
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
        switch (url){
            case "about":
                return "user/category_content";
            case "spring-boot":
                return "user/category_content2";
            case "java8":
                return "user/category_content2";
            case "archives":
                //文章归档
                Map<String, List<Content>> archives = archives();
                model.addAttribute("archives", archives);
                return "user/content_list";

        }
        return "";
    }


    /**
     * TODO 这个操作比较耗时，后面想办法优化。采取页面静态化也是一个办法
     * 反正不应该去查询数据库，应该用逻辑来控制
     * 查询出全部的文章，然后在根据时间排序
     */
    private Map<String, List<Content>> archives(){
        Map<String, List<Content>> map = new HashMap<>();
        Set<String> dateSet = new HashSet<>();
        Category category = new Category();
        category.setId(1);
        List<Content> contents = contentService.findContentByCriteria(category);
        //遍历所有的文章，取出他们的 createDt，转换成 年月 的方式
        for (Content content : contents) {
            Integer createDt = content.getCreateDt();
            String dateKey = DateUtils.formatDateByUnixTime(createDt, "yyyy年MM月");
            dateSet.add(dateKey); //把重复的时间去掉
        }
        for(String strDate : dateSet){
            List<Content> contentList = new ArrayList<>();
            for (Content c : contents) {
                String date = DateUtils.formatDateByUnixTime(c.getCreateDt(), "yyyy年MM月");
                if(date.equals(strDate)){
                    contentList.add(c);
                }
            }
            map.put(strDate, contentList);
        }
        return map;
    }


    /**
     * issues 页面
     */
    @RequestMapping("/issues")
    public String issues(Model model){
        findCategoryList(model);
        findTagList(model);


        return "user/issues";
    }
}
