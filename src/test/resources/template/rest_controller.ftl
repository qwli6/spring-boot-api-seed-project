package ${packageName}.controller;


import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import java.util.List;
import ${packageName}.model.${packageName};
import ${packageName}.service.${packageName}Service;



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

    @Resource
    private ${className}Service ${className?uncap_first}Service;

    /**
     * 获取 ${className?lower_case} 列表
     * @return
     **/
    @GetMapping("/list")
    public Result find${className}List(){
        List<${className}> ${className?lower_case}List = ${className?uncap_first}Service.findAll();
        //补充其他逻辑
        if(${className?lower_case}List != null && ${className?lower_case}List.size()>0){
            return ResultGenerator.genSuccessResult(${className?lower_case}List);
        }else{
            return null;
        }
    }

    /**
    * 去新增 ${className} 页面
    * @return 页面
    **/
    @PostMapping("/save")
    public Result save${className}(${className} ${className?uncap_first}){
        ${className?uncap_first}Service.save${className}(${className?uncap_first});
        return "redirect:/${className?lower_case}/list";
    }

    /**
    * 保存修改
    * @return 页面
    **/
    @PostMapping("/update")
    public Result update${className}(${className} ${className?lower_case}){
        //根据用户 id 查找用户
        int updateCount = ${className?uncap_first}Service.update${className}(${className?lower_case});
        if(updateCount == 1){
            return "redirect:/${className?lower_case}/list";
        }
        return null;
    }

}