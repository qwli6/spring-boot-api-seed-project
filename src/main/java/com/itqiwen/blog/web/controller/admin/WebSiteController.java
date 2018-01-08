package com.itqiwen.blog.web.controller.admin;

import com.alibaba.fastjson.JSON;
import com.itqiwen.blog.entity.Options;
import com.itqiwen.blog.entity.RestResponse;
import com.itqiwen.blog.service.OptionService;
import com.itqiwen.blog.web.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 网站共用信息基础设置控制器
 * @author liqiwen
 */
@SuppressWarnings("all")
@Controller
@RequestMapping("admin")
public class WebSiteController extends BaseController{

    @Resource
    private OptionService optionService;


    /**
     * 获取共用基础信息列表
     * @return
     */
    @RequestMapping("/options/list")
    @ResponseBody
    public String optionList(){
        List<Options> options = optionService.findAllOption();
        return JSON.toJSONString(options);
    }

    /**
     * 跳转添加 options 页面
     * @return
     */
    @RequestMapping(value = "/options/add", method = RequestMethod.GET)
    public String addOption(){

        return "option-edit";
    }


    /**
     * 保存或者修改页面
     * @param options
     * @return
     */
    @RequestMapping(value = "/options/save", method = RequestMethod.POST)
    public String saveOption(Options options){

        Integer optionsId = options.getId();
        //修改options
        if(!StringUtils.isEmpty(String.valueOf(optionsId)) && StringUtils.isNotBlank(String.valueOf(optionsId))) {
            optionService.updateOptions(options);
        }else{
            //保存 options
            optionService.saveOptions(options);
        }
        //重定向到 options 列表
        return "redirect:/admin/options/list";
    }


    /**
     * 删除 Options
     * 返回基础json 数据
     * @param id
     * @return
     */
    @RequestMapping(value = "/options/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public RestResponse<Options> deleteOptions(@PathVariable("id") String id){

        Options options = optionService.findOptionsById(Long.parseLong(id));
        if(options != null){
            optionService.deleteOptions(options);
            return RestResponse.ok("删除成功");
        }
        return RestResponse.fail("删除失败");
    }
}
