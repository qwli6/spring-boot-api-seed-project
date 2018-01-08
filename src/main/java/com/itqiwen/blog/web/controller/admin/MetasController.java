package com.itqiwen.blog.web.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.itqiwen.blog.entity.Meta;
import com.itqiwen.blog.entity.RestResponse;
import com.itqiwen.blog.service.MetaService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 分类，标签，友链 控制器
 * @author liqiwen
 * @date 2017-10-12
 */
@SuppressWarnings("all")
@Controller
@RequestMapping("admin")
public class MetasController {

    @Resource
    private MetaService metaService;

    /**
     * 添加分类
     * @return
     */
    @RequestMapping(value = "/category/add", method = RequestMethod.GET)
    public String addCategory(){
        return "admin/category-edit";
    }

    @RequestMapping(value = "/categoyr/add", method = RequestMethod.POST)
    public RestResponse<Meta> saveMeta(Meta meta){
        metaService.saveMeta(meta);
        return RestResponse.ok();
    }


    /**
     * 根据 type 查找 type 有三种类型，CATEGORY LINK TAG
     */
    @RequestMapping(value = "/meta/{type}", method = RequestMethod.GET)
    @ResponseBody
    public String findMetaByType(@PathVariable("type")String type){
        List<Meta> metaList = metaService.findMetaByType(type);
        return JSON.toJSONString(metaList);
    }
}
