package com.itqiwen.blog.web.controller.admin;

import com.itqiwen.blog.config.Config;
import com.itqiwen.blog.config.ContentState;
import com.itqiwen.blog.config.ContentType;
import com.itqiwen.blog.domain.Category;
import com.itqiwen.blog.domain.Content;
import com.itqiwen.blog.domain.RestResponse;
import com.itqiwen.blog.service.ContentService;
import com.itqiwen.blog.service.LogService;
import com.itqiwen.blog.utils.DateUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Date;
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
        return "redirect:/admin/content/1";
    }

    @RequestMapping(value = "/content/{pageCode}", method = RequestMethod.GET)
    public String findContentList(@PathVariable("pageCode")Integer pageCode,
                                  Model model){
        Page<Content> page = contentService.findContentByCriteria(pageCode, Config.ADMIN_PAGE_SIZE);
        List<Content> contents = page.getContent();
        model.addAttribute("contents", contents);
        return "admin/index";
    }

    /**
     * 新增内容
     * @return
     */
    @RequestMapping(value = "/content/new", method = RequestMethod.GET)
    public String newContent(){
        return "admin/content_edit";
    }

    /**
     * 查看垃圾箱文章列表
     */
    @RequestMapping(value = "/content/garbage", method = RequestMethod.GET)
    public String garbage(){

        return "admin/content_garbage";
    }

    /**
     * 保存文章
     * @param content
     * @return
     */
    @RequestMapping(value = "/content/save", method = RequestMethod.POST)
    public String saveContent(Content content, @RequestParam("category_id")Integer category_id, @RequestParam("tags")String tags){
        //判断content id 是否为空，如果不为null，说明是修改后保存文章，如果为 null 说明是新增内容
        if(content.getCid() != null){
            //修改后保存文章，修改content 的最近一次修改时间
            content.setUpdateDt(DateUtils.getUnixTimeByDate(new Date()));
            contentService.updateContent(content);
        }else{
            //新增文章
            content.setCreateDt(DateUtils.getUnixTimeByDate(new Date()));
            content.setUpdateDt(DateUtils.getUnixTimeByDate(new Date()));
            if(content.getContentHtml().length() < 20) {
                content.setDigest(content.getContentHtml());
            }else {
                content.setDigest(content.getContentHtml().substring(0, 20));//设置摘要
            }
            content.setState(ContentState.PUBLISH.getState());
            content.setType(ContentType.PUBLIC.getType());
            content.setVisitCount(0);
            content.setRemarkCount(0);

            Category category = new Category();
            category.setId(category_id);
            content.setCategory(category);



//            Metas metas = new Metas();
//            metas.setMid(Integer.parseInt(category));
//            content.setMetas(metas);

            contentService.saveContent(content);
        }

        return "redirect:/admin/content/1";
    }


    /**
     * 查看用户日志操作
     */
    @RequestMapping(value = "/log/1", method = RequestMethod.GET)
    public String logList(){

        return "admin/log";
    }


    /**
     * 查找全部分类
     */
    @RequestMapping(value = "/category/list", method = RequestMethod.POST)
    public RestResponse getCategoryList(){
//        List<Metas> metaByType = metaService.findMetaByType(Types.CATEGORY.getType());
        return RestResponse.ok();
    }
}
