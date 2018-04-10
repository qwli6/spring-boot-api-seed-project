package ${packageName}.web.controller;


import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.github.pagehelper.PageHelper;
import org.springframework.web.bind.annotation.RequestParam;
import javax.annotation.Resource;
import java.util.List;
import ${packageName}.core.AbstractController;
import ${packageName}.model.${className};
import ${packageName}.service.${className}Service;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;



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
public class ${className}Controller extends AbstractController {

    @Resource(name = "${className?uncap_first}Service")
    private ${className}Service ${className?uncap_first}Service;

    /**
    * 分页查找列表
    * @param page 当前页
    * @param size 页大小
    * @return 列表
    */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result find${className}ByPage(@RequestParam(value = "page", defaultValue = "0", required = true) Integer page,
                                         @RequestParam(value = "size", defaultValue = "0", required = true) Integer size){
            PageHelper.startPage(page,size);
            List<${className}> ${className?uncap_first}List = ${className?uncap_first}Service.findAll();
            return ResultGenerator.genSuccessResult(${className?uncap_first}List);
    }

    /**
    * 该方法是一个演示方法，演示条件查询，
    * 因为条件查询的方法具有不确定性，因此无法生成模板方法，
    * 如果需要条件查询或排序，可以参考该方法的具体实现
    * @param age 年龄 查询年龄在 30 - 40 岁之前，并且按年龄由高到低倒序分页排序
    *            传入 age 应该是  35
    * @return 列表
    */
    @RequestMapping(value = "/find")
    public Result find${className}sByCondition(@RequestParam(value = "age",required = true) Integer age,
                                       @RequestParam(value = "page", required = true) Integer page,
                                       @RequestParam(value = "size", required = true) Integer size){
           Condition condition = new Condition(${className}.class);
           condition.orderBy("age").desc();
           Example.Criteria criteria = condition.createCriteria();
           criteria.andBetween("age", age- 5, age + 5);
           PageHelper.startPage(page, size);
           List<${className}> ${className?uncap_first}List = ${className?uncap_first}Service.findByCondition(condition);
           if(${className?uncap_first}List!= null && ${className?uncap_first}List.size() > 0){
                return ResultGenerator.genSuccessResult(${className?uncap_first}List);
           }else{
                return ResultGenerator.genFailResult("不存在该数据");
           }
    }


    /**
    * 保存
    * @param user model
    * @return
    */
    @RequestMapping(value="/save", method=RequestMethod.POST)
    public Result save${className}(${className} ${className?uncap_first}){
        ${className?uncap_first}Service.save(${className?uncap_first});
        return ResultGenerator.genSuccessResult();
    }

<#list dataMap as key>
    <#if key_index == 0>
    /**
    * 删除
    * @param id id
    * @return
    */
    @RequestMapping(value="/delete", method=RequestMethod.POST)
    public Result delete${className}By${key.columnJavaName?cap_first}(@RequestParam("${key.columnJavaName}") ${key.columnType} ${key.columnJavaName}){
        ${className?uncap_first}Service.deleteBy${key.columnJavaName?cap_first}(${key.columnJavaName});
        return ResultGenerator.genSuccessResult();
    }

    /**
    * 详情
    * @param id id
    * @return
    */
    @RequestMapping(value="/detail", method=RequestMethod.POST)
    public Result find${className}By${key.columnJavaName?cap_first}(@RequestParam("${key.columnJavaName}") ${key.columnType} ${key.columnJavaName}){
        ${className} ${className?lower_case} = ${className?uncap_first}Service.findBy${key.columnJavaName?cap_first}(${key.columnJavaName});
        return ResultGenerator.genSuccessResult(${className?lower_case});
    }
    </#if>
</#list>

    /**
    * 更新 ${className?uncap_first}
    * @param ${className?uncap_first} ${className?uncap_first}
    * @return
    */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result edit${className}(${className} ${className?uncap_first}){
        ${className?uncap_first}Service.update(${className?uncap_first});
        return ResultGenerator.genSuccessResult();
    }

}