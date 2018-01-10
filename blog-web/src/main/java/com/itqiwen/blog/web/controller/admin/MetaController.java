package com.itqiwen.blog.web.controller.admin;

import com.alibaba.fastjson.JSON;
import com.itqiwen.blog.domain.Log;
import com.itqiwen.blog.domain.Metas;
import com.itqiwen.blog.service.LogService;
import com.itqiwen.blog.service.MetaService;
import com.itqiwen.blog.utils.JsonUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/admin")
@RestController
public class MetaController {

    @Resource
    private MetaService metaService;

    @Resource
    private LogService logService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String findAll(){
        System.out.println("查询全部。。。。");
        List<Metas> metasList = metaService.findAll();
        return JSON.toJSONString(metasList);
    }

    /**
     * 查询链接，分类，标签列表
     * @param type
     * @return
     */
    @RequestMapping(value = "{type}/list", method = RequestMethod.GET)
    public String findMetaListByType(@PathVariable("type") String type){
        List<Metas> metasList = metaService.findMetaByType(type);
        return JSON.toJSONString(metasList);
    }


    /**
     * 日志操作
     * 默认查看最新20条的日志
     */
    @RequestMapping(value = "/log/list", method = RequestMethod.GET)
    public String logList(){
        List<Log> logByPage = logService.findLogByPage();
        return JsonUtil.toJSONString(logByPage);
    }
}
