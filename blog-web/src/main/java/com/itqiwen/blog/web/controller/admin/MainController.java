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
        Page<Content> contentPage = contentService.findContentByCriteria(pageCode, Config.ADMIN_PAGE_SIZE);
        model.addAttribute("contentPage", contentPage);
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
     * 编辑文章
     */
    @RequestMapping(value = "/content/modify/{cid}", method = RequestMethod.GET)
    public String modifyContent(@PathVariable("cid")String cid, Model model){
        Content content = contentService.findContentById(cid);
        if(content != null){
            model.addAttribute("content", content);
        }
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
            Content oldContent = contentService.findContentById(String.valueOf(content.getCid()));
            //根据查询出来的 oldContent 和文章页面的提交过来的 content 进行判断，如果不同，则是更改！
            /**
             * 修改页面的要素
             * 1， 标题
             * 2. 摘要： 页面不存在，但是有这个字段
             * 3. 内容： md
             * 4. 内容： html
             * 5. 访问 url
             * 6. 最后修改时间
             */

            oldContent.setUpdateDt(DateUtils.getUnixTimeByDate(new Date())); //设置上一次修改的时间


            oldContent.setTitle(content.getTitle()); //设置修改后的文章标题
            oldContent.setContentMd(content.getContentMd()); //设置修改的 md 内容
            oldContent.setContentHtml(content.getContentHtml()); //设置修改后的 html 内容
            oldContent.setVisitUrl(content.getVisitUrl()); //设置修改后的访问 url




            contentService.updateContent(oldContent);
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
            contentService.saveContent(content);
        }

        return "redirect:/admin/content/1";
    }


    /**
     * 删除文章
     */
    @RequestMapping(value = "/content/del/{cid}", method = RequestMethod.GET)
    public String deleteContent(@PathVariable("cid")String cid){

        Content content = contentService.findContentById(cid);
        if(content != null){
            contentService.deleteContent(content);
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
