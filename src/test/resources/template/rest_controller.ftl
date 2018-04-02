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
}