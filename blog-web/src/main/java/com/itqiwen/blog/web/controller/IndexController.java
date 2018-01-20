package com.itqiwen.blog.web.controller;

import com.itqiwen.blog.config.Config;
import com.itqiwen.blog.domain.Archive;
import com.itqiwen.blog.domain.Article;
import com.itqiwen.blog.domain.Menu;
import com.itqiwen.blog.domain.Tag;
import com.itqiwen.blog.service.IArchiveService;
import com.itqiwen.blog.service.IMenuService;
import com.itqiwen.blog.service.IArticleService;
import com.itqiwen.blog.service.ITagService;
import com.itqiwen.blog.utils.DateUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.jws.WebParam;
import java.util.*;

/**
 * 前台页面控制器
 * @author liqiwen
 */
@Controller
public class IndexController {
    private static int pageCode = 1;

    @Resource
    private IArticleService articleService;
    @Resource
    private IMenuService menuService;
    @Resource
    private ITagService tagService;
    @Resource
    private IArchiveService archiveService;

    /**
     * 查看首页
     */
    @RequestMapping(value = {"/index","/"}, method = RequestMethod.GET)
    public ModelAndView mainHome(){
        ModelAndView modelAndView = new ModelAndView();
        List<Menu> menuList = menuService.findMenuList();
        modelAndView.addObject("menuList", menuList);
        articleService.findContentByCriteria(pageCode, Article.PAGE_SIZE);
        List<Archive> archives = archiveService.findArchiveList();
        modelAndView.addObject("archives", archives);
        findTagList(modelAndView);
        modelAndView.setViewName("user/index");

//        findCategoryList(model);
//        findTagList(model);
//        Menu category = new Menu();
//        category.setId(1); //查询 index 首页展示的文章
//        Page<Content> contentPage = articleService.findContentByCriteria(1,
//                Config.PAGE_SIZE, category);
//        model.addAttribute("contentPage", contentPage);
//        //文章归档
//        Map<String, List<Content>> archives = archives();
//        model.addAttribute("archives", archives);
//        return "user/index";
        return modelAndView;
    }


    private void findTagList(ModelAndView modelAndView){
        List<Tag> tagList = tagService.findTagList();
        modelAndView.addObject("tagList", tagList);
    }

    private void findArchiveList(ModelAndView modelAndView){
        List<Archive> archives = archiveService.findArchiveList();
        modelAndView.addObject("archives",archives);
    }

    public void findMenuList(ModelAndView modelAndView){
        List<Menu> menuList = menuService.findMenuList();
        modelAndView.addObject("menuList", menuList);
    }



    /**
     * 首页文章分页方法
     * @return
     */
    @RequestMapping(value = "/page/{pageCode}", method = RequestMethod.GET)
    public String mainHome(Model model, @PathVariable("pageCode")Integer pageCode){
        Map<String, List<Article>> archives = archives();
        model.addAttribute("archives", archives);
        if(pageCode > 1) {
            Menu category = new Menu();
            category.setId(1);
            Page<Article> contentPage = articleService.findContentByCriteria(pageCode,
                    Config.PAGE_SIZE, category);
            model.addAttribute("contentPage", contentPage);
        }else{
            return "redirect:/";
        }
        return "user/index";
    }

    /**
     * 根据访问 url 或者 id 去查找 Article，
     * 查看文章详情
     * @return
     */
    @RequestMapping(value = "article/show/{url}", method = RequestMethod.GET)
    public ModelAndView showArticle(@PathVariable("url")String url){
        ModelAndView modelAndView = new ModelAndView();
        Article article = articleService.findArticleByUrl(url);
        new Thread(() -> {
            Long rate = article.getRate();
            rate ++;
            articleService.updateRate(article.getArticleId(), rate);
        }).start();
        modelAndView.setViewName("user/post");
        modelAndView.addObject("content", article);
        return modelAndView;
    }


    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public ModelAndView aboutMe(){
        ModelAndView modelAndView = new ModelAndView();
        findMenuList(modelAndView);
        findTagList(modelAndView);
        findArchiveList(modelAndView);
        modelAndView.setViewName("user/index");
        Page<Article> articlePage = articleService.findArticleByCriteria(pageCode, Article.PAGE_SIZE, (short) 2);
        System.out.println("articlePage:" + articlePage.getContent().size());
        modelAndView.addObject("articlePage", articlePage);
        return modelAndView;
    }


    @RequestMapping(value = "/chatlist", method = RequestMethod.GET)
    public ModelAndView chatList(){
        ModelAndView modelAndView = new ModelAndView();
        findMenuList(modelAndView);
        findTagList(modelAndView);
        findArchiveList(modelAndView);
        modelAndView.setViewName("user/chatList");
        Page<Article> articlePage = articleService.findArticleByCriteria(pageCode, Article.PAGE_SIZE, (short) 8);
        modelAndView.addObject("articlePage", articlePage);
        return modelAndView;
    }


    @RequestMapping(value = "/menu/{url}", method = RequestMethod.GET)
    public ModelAndView menu(@PathVariable("url")String url){
        ModelAndView modelAndView = new ModelAndView();
        Menu menu = menuService.findMenuByUrl(url);
        switch (menu.getId()){
            //关于
            case 2:
                modelAndView.setViewName("redirect:/about");
                return modelAndView;
            //好友
            case 3:
                modelAndView.setViewName("redirect:/friends");
                return modelAndView;
            //springboot
            case 4:
                modelAndView.setViewName("redirect:/springboot");
                return modelAndView;
            //java8
            case 5:
                modelAndView.setViewName("redirect:/java8");
                break;
            //日志列表
            case 6:
                modelAndView.setViewName("redirect:/list");
                return modelAndView;
            case 7://推荐书单
                modelAndView.setViewName("redirect:/booklist");
                return modelAndView;
            case 8://白话
                modelAndView.setViewName("redirect:/chatlist");
                return modelAndView;
            default:
                modelAndView.setViewName("user/index");
                return modelAndView;
        }
//        List<Article> contents = articleService.findContentsByCategory(category);
        //文章归档
        Map<String, List<Article>> archives = archives();
//        model.addAttribute("archives", archives);
//        model.addAttribute("contents", contents);
//        switch (url){
//            case "about":
//                return "user/category_content";
//            case "spring-boot":
//                return "user/course";
//            case "java8":
//                return "user/course";
//            case "archives":
//                return "user/archives";
//            case "books":
//                return "user/course";
//            case "friends":
//                return "user/course";
//
//
//        }
//        return "";
        return null;
    }


    /**
     * 文章归档
     * 查询所有在首页的文章，并按年月排布
     * list
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView archiveList(){

        ModelAndView modelAndView = new ModelAndView();


        return modelAndView;

    }

    /**
     * TODO 这个操作比较耗时，后面想办法优化。采取页面静态化也是一个办法
     * 不应该去多次查询数据库，用逻辑来控制
     * 查询出全部的文章，然后在根据时间排序
     */
    private Map<String, List<Article>> archives(){
        Map<String, List<Article>> map = new HashMap<>();
        Set<String> dateSet = new HashSet<>();
        Menu category = new Menu();
        category.setId(1);
//        List<Article> contents = articleService.findContentByCriteria(null);
//        //遍历所有的文章，取出他们的 createDt，转换成 年月 的方式
//        for (Article content : contents) {
//            Integer createDt = content.getCreateDate();
//            String dateKey = DateUtils.formatDateByUnixTime(createDt, "yyyy年MM月");
//            dateSet.add(dateKey); //把重复的时间去掉
//        }
//        for(String strDate : dateSet){
//            List<Article> contentList = new ArrayList<>();
//            for (Article c : contents) {
//                String date = DateUtils.formatDateByUnixTime(c.getCreateDate(), "yyyy年MM月");
//                if(date.equals(strDate)){
//                    contentList.add(c);
//                }
//            }
//            map.put(strDate, contentList);
//        }
        return map;
    }

    /**
     * 根据标签查找当前标签下的文章
     */
    @RequestMapping(value = "/tag/{urlName}", method = RequestMethod.GET)
    public ModelAndView findArticlesByTag(@PathVariable("urlName")String urlName){
        ModelAndView modelAndView = new ModelAndView();
        new Thread(() -> {
            findTagList(modelAndView);
            findArchiveList(modelAndView);
        }).start();

        modelAndView.setViewName("user/index");
        return modelAndView;
    }

    /**
     * 根据分类查找文章
     */
    @RequestMapping(value = "/archive/{urlName}", method = RequestMethod.GET)
    public ModelAndView findArticleByArchive(@PathVariable("urlName")String urlName){
        ModelAndView modelAndView = new ModelAndView();
        new Thread(() ->{
            findTagList(modelAndView);
            findArchiveList(modelAndView);
        });

        Archive archive = archiveService.findArchiveByUrlName(urlName);
        Page<Article> articlePage = articleService.findArticleByCriteria(1,
                Article.PAGE_SIZE, Short.parseShort(archive.getArchiveId().toString()));
        modelAndView.addObject("articlePage", articlePage);
        modelAndView.setViewName("user/index");
        return modelAndView;
    }

    @RequestMapping(value = "/archive/{urlName}/{pageCode}", method = RequestMethod.GET)
    public ModelAndView findArticleByArchiveByPage(@PathVariable("urlName")String urlName,
                                                   @PathVariable("pageCode")Integer pageCode){
        ModelAndView modelAndView = new ModelAndView();
        new Thread(() ->{
           findTagList(modelAndView);
           findArchiveList(modelAndView);
        }).start();
        if(pageCode < 1){
            pageCode = 1;
        }
        Archive archive = archiveService.findArchiveByUrlName(urlName);
        Page<Article> articlePage = articleService.findArticleByCriteria(pageCode, Article.PAGE_SIZE, Short.parseShort(archive.getArchiveId().toString()));
        modelAndView.addObject("articlePage", articlePage);
        modelAndView.setViewName("user/index");
        return modelAndView;
    }

    @RequestMapping(value = "/tag/{urlName}/{pageCode}", method = RequestMethod.GET)
    public ModelAndView findArticleByTagByPage(@PathVariable("urlName")String urlName,
                                               @PathVariable("pageCode")Integer pageCode){
        ModelAndView modelAndView = new ModelAndView();
        new Thread(() ->{
            findTagList(modelAndView);
            findArchiveList(modelAndView);
        }).start();


        modelAndView.setViewName("user/index");
        return modelAndView;
    }
}
