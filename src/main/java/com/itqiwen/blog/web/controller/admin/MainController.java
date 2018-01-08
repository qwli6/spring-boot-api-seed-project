package com.itqiwen.blog.web.controller.admin;

import com.alibaba.fastjson.JSON;
import com.itqiwen.blog.entity.Content;
import com.itqiwen.blog.entity.RestResponse;
import com.itqiwen.blog.service.ContentService;
import com.itqiwen.blog.utils.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * 后台首页控制器
 */
@SuppressWarnings("all")
@Controller
@RequestMapping("admin")
public class MainController {

    @Resource
    private ContentService contentService;

    /**
     * 首页
     * @return
     */
    @RequestMapping(value = "/main")
    @ResponseBody
    public String main(){
        List<Content> allContents = contentService.findAllContents();
        return JSON.toJSONString(allContents);
    }

    /**
     * 根据 url 查找文章详细信息
     * @param visitUrl
     * @return  文章实体类
     */
    @RequestMapping(value = "/content/{visitUrl}", method = RequestMethod.GET)
    @ResponseBody
    public String detail(@PathVariable("visitUrl")String visitUrl){
        Content contentByVisitUrl = contentService.findContentByVisitUrl(visitUrl);
        return JSON.toJSONString(contentByVisitUrl);
    }


    /**
     * 保存文章实体
     * @param content
     * @return
     */
    @RequestMapping(value = "/content/save", method = POST)
    public RestResponse<Content> newContent(Content content){
        contentService.saveContent(content);
        return RestResponse.ok();
    }


    /**
     * 修改文章
     * @param content
     * @return
     */
    @RequestMapping(value = "/content/update", method = POST)
    public RestResponse<Content> editContent(Content content){

        //手动设置文章修改时间， 文章上次修改时间改为 int 类型，取 unix 格式比较好
        int date = DateUtils.getUnixTimeByDate(new Date());
        System.out.println("上次修改文章时间：" + date);
//        content.setUpdateDt();
        contentService.updateContent(content);
        return RestResponse.ok();
    }

    /**
     * 修改文章界面
     * @return
     */
    @RequestMapping(value = "/content/edit", method = GET)
    public String editContent(String cid, HttpServletRequest request){
        Content contentById = contentService.findContentById(cid);
        if(contentById != null){
            request.setAttribute("content", contentById);
        }
        return "admin/content-edit";
    }

    /**
     * 新文章页面
     * @return
     */
    @RequestMapping(value = "/content/new", method = GET)
    public String newContent(){
        return "admin/content-edit";
    }
}
