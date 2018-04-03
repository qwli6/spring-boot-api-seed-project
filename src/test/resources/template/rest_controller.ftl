package ${packageName}.web.controller;


import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import java.util.List;
import ${packageName}.model.${className};
import ${packageName}.service.${className}Service;


/**
*  描述:
        ${className}Controller
*  Author: ${author}
*  Email: ${email}
*  Date: ${.now?string("yyyy-MM-dd")}
*  Company: ${company}
*/
@RestController
@RequestMapping("/${className?lower_case}")
public class ${className}Controller {

    @Resource(name = "${className?lower_case}Service")
    private ${className}Service ${className?uncap_first}Service;

    @RequestMapping(value="/list", method=RequestMethod.POST)
    public Result get${className}List(){
        List<${className}> ${className?lower_case}List = ${className?uncap_first}Service.find${className}List();
        return ResultGenerator.genSuccessResult(${className?lower_case}List);
    }

    @RequestMapping(value="/save", method=RequestMethod.POST)
    public Result save${className}(${className} ${className?lower_case}){
        ${className?uncap_first}Service.save${className}(${className?lower_case});
        return ResultGenerator.genSuccessResult();
    }


<#list dataMap as key>
    <#if key_index == 0>
    @RequestMapping(value="/delete", method=RequestMethod.POST)
    public Result delete${className}By${key.columnJavaName?cap_first}(${key.columnType} ${key.columnJavaName}){
        ${className?uncap_first}Service.delete${className}By${key.columnJavaName?cap_first}(${key.columnJavaName});
        return ResultGenerator.genSuccessResult();
    }

    @RequestMapping(value="/detail", method=RequestMethod.POST)
    public Result find${className}By${key.columnJavaName?cap_first}(${key.columnType} ${key.columnJavaName}){
        ${className} ${className?lower_case} = ${className?uncap_first}Service.find${className}By${key.columnJavaName?cap_first}(${key.columnJavaName});
        return ResultGenerator.genSuccessResult(${className?lower_case});
    }
    </#if>
</#list>

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public Result edit${className}(${className} ${className?lower_case}){
        ${className?uncap_first}Service.edit${className}(${className?lower_case});
        return ResultGenerator.genSuccessResult();
    }


}