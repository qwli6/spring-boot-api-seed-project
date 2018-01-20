package com.itqiwen.blog.web.controller;

import com.itqiwen.blog.config.Config;
import com.itqiwen.blog.domain.Archive;
import com.itqiwen.blog.domain.Article;
import com.itqiwen.blog.domain.Menu;
import com.itqiwen.blog.domain.RestResult;
import com.itqiwen.blog.service.IArchiveService;
import com.itqiwen.blog.service.IArticleService;
import com.itqiwen.blog.service.ILogService;
import com.itqiwen.blog.service.IMenuService;
import com.itqiwen.blog.service.impl.ArchiveServiceImpl;
import com.itqiwen.blog.utils.DateUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import sun.rmi.runtime.Log;

import javax.annotation.Resource;
import javax.jws.WebParam;
import java.util.Date;
import java.util.List;

@RequestMapping("admin")
@Controller
public class AdminController {
    @Resource
    private ILogService logService;
    @Resource
    private IArticleService articleService;
    @Resource
    private IMenuService menuService;

    @Resource
    private IArchiveService archiveService;

    /**
     * 重定向请求文章列表
     * 要求登录
     */
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView main(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin/article/1");
        return modelAndView;
    }

    /**
     * 查找文章列表
     * 要求登录
     * 分页
     */
    @RequestMapping(value = "/article/list/{pageCode}", method = RequestMethod.GET)
    public ModelAndView findArticleList(@PathVariable("pageCode")Integer pageCode){
        ModelAndView modelAndView =new ModelAndView();
        modelAndView.setViewName("admin/article_list");
        Page<Article> articlePage = articleService.
                findContentByCriteria(pageCode, Article.ADMIN_PAGE_SIZE);
        modelAndView.addObject("articlePage", articlePage);
        return modelAndView;
    }


    /**
     * 要求登录
     * 写新文章
     */
    @RequestMapping(value = "/article/new", method = RequestMethod.GET)
    public ModelAndView newArticle(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/article_edit");
        //TODO 改成异步请求，获取文章标签也应该改成异步请求
        List<Menu> menuList = menuService.findMenuList();
        modelAndView.addObject("menuList", menuList);
        return modelAndView;
    }

    /**
     * 编辑文章
     * 要求登录
     */
    @RequestMapping(value = "/article/{articleId}", method = RequestMethod.GET)
    public ModelAndView updateArticle(@PathVariable("articleId")Integer articleId){
        ModelAndView modelAndView = new ModelAndView("admin/article_edit");
        Article article = articleService.findArticleById(articleId);
        if(article != null){
            modelAndView.addObject("article", article);
        }
        return modelAndView;
    }

    /**
     * 删除文章，文章会保存到垃圾箱中
     * 查看垃圾箱文章列表
     * 需要判断登录状态
     */
    @RequestMapping(value = "/article/trash", method = RequestMethod.GET)
    public ModelAndView trash(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/article_trash");
        return modelAndView;
    }


    /**
     * 保存文章，需要登录
     * 访客模式不允许操作 post 请求
     */
    @RequestMapping(value = "/article/save", method = RequestMethod.POST)
    public ModelAndView saveArticle(Article article,
                              @RequestParam("menuId") Integer menuId,
                              @RequestParam("tags")String tags){
        ModelAndView modelAndView = new ModelAndView();
        if(article.getArticleId() != null){
            Article oldArticle = articleService.findArticleById(article.getArticleId());
            oldArticle.setLastEditDate(DateUtils.getUnixTimeByDate(new Date()));
            oldArticle.setTitle(article.getTitle()); //设置修改后的文章标题
            oldArticle.setMarkdown(article.getMarkdown()); //设置修改的 md 内容
            oldArticle.setHtml(article.getHtml()); //设置修改后的 html 内容
            articleService.updateArticle(oldArticle);
        }else{
            //保存文章，主要关注分类，日志列表以及标签
            //先把文章保存，然后在开线程去保存一些附属信息
            articleService.saveArticle(article);

            new Thread(() -> {
                //保存归档
                String format = DateUtils.dateFormat(new Date(), "yyyy年MM月");
                Archive archive = archiveService.findArchiveByTitle(format);
                if(archive == null){
                    Archive newArchive = new Archive();
                    newArchive.setCount(1L);
                    newArchive.setTitle(format);
                    newArchive.setUrlName(DateUtils.dateFormat(new Date(), "yyyyMM"));
                    archiveService.saveArchive(newArchive);
                    //更改 count，并且更改 Article 中的字段 archiveId
//                    articleService.updateArchiveId()
                }else{
                    //归档存在，更改 article archiveId 并且更改 count
                    articleService.updateArchiveId(archive.getArchiveId(), article.getArticleId());
                    Long count = archive.getCount();
                    count++;
                    archive.setCount(count);
                    archiveService.updateCount(archive);
                }
            }).start();
        }
        modelAndView.setViewName("redirect:/admin/article/list/1");
        return modelAndView;
    }

    /**
     * 预览文章，需要登录，post 请求
     * 访客身份不允许操作
     */
    @RequestMapping(value = "/article/preview", method = RequestMethod.POST)
    public ModelAndView previewArticle(Article article){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("null");
        return modelAndView;
    }

    /**
     * 删除文章
     * post 请求
     */
    @RequestMapping(value = "/article/delete", method = RequestMethod.POST)
    public ModelAndView deleteArticle(@RequestParam("articleId") Integer articleId){
        ModelAndView modelAndView = new ModelAndView();
        Article article = articleService.findArticleById(articleId);
        if(article != null){
            articleService.deleteArticle(article);
        }
        modelAndView.setViewName("redirect:/admin/article/1");
        return modelAndView;
    }
}
