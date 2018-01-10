package com.itqiwen.blog.web.controller.admin;

import com.itqiwen.blog.config.Config;
import com.itqiwen.blog.config.ContentState;
import com.itqiwen.blog.config.ContentType;
import com.itqiwen.blog.domain.Content;
import com.itqiwen.blog.domain.RestResponse;
import com.itqiwen.blog.service.ContentService;
import com.itqiwen.blog.utils.DateUtils;
import com.itqiwen.blog.utils.JsonUtil;
import com.itqiwen.blog.web.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 文章页面控制器
 * @author liqiwen
 */
@RequestMapping("admin")
@RestController
public class ContentController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContentController.class);


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
        return JsonUtil.toJSONString(contentList);
    }


    /**
     * 预览文章，将文章存入草稿，进行预览，
     * 预览过后，用户确定发布的话，将文章状态更改成 发布
     * @param content
     * @return
     */
    @RequestMapping(value = "/content/preview", method = RequestMethod.POST)
    public String previewContent(Content content, HttpServletRequest request){
        LOGGER.info("starting preview content");
        content.setType(ContentType.PUBLIC.getType());
        content.setState(ContentState.DRAFT.getState());
        content.setCreateDt(DateUtils.getUnixTimeByDate(new Date()));
        content.setUpdateDt(DateUtils.getUnixTimeByDate(new Date()));
        //如果摘要不存在，截取文章正文部分 前 100 个字符作为摘要
        if(StringUtils.isEmpty(content.getDigest())){
            //判断文章长度，如果文章正文长度大于100， 截取 100
            if(content.getContentMd().length() > 100){
                content.setDigest(content.getContentMd().substring(0, 100));
            }else{
                //小于 100 ，全部赋值给 正文
                content.setDigest(content.getContentMd());
            }
        }
        contentService.saveContent(content);
        request.setAttribute("content", content);
        return "common/post";
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
     * 编辑文章
     */
    @RequestMapping(value = "/content/edit/{cid}", method = RequestMethod.GET)
    public String editContent(@PathVariable("cid")String cid, HttpServletRequest request){

        //根据文章id 去查找文章内容
        Content content = contentService.findContentById(cid);
        request.setAttribute("content", content);
        return "admin/content_edit";

    }

    /**
     * 用户不预览，直接发布内容
     * @param content
     * @return
     */
    @RequestMapping(value = "/content/new", method = RequestMethod.POST)
    public RestResponse newContent(Content content){

        return RestResponse.ok();
    }

    /**
     * 修改文章状态
     * @param state
     * @return
     */
    @RequestMapping(value = "/content/update", method = RequestMethod.GET)
    public String updateContentState(@PathVariable("state")String state){

        return null;
    }


    @RequestMapping(value = "/content/save", method = RequestMethod.POST)
    public String saveContent(Content content){
        //判断content id 是否为空，如果不为null，说明是修改后保存文章，如果为 null 说明是新增内容
        if(StringUtils.isEmpty(String.valueOf(content.getCid()))){
            //修改后保存文章，修改content 的最近一次修改时间
            content.setUpdateDt(DateUtils.getUnixTimeByDate(new Date()));
            contentService.updateContent(content);
        }else{
            //新增文章
            content.setCreateDt(DateUtils.getUnixTimeByDate(new Date()));
            content.setUpdateDt(DateUtils.getUnixTimeByDate(new Date()));

            contentService.saveContent(content);
        }
    }
}
