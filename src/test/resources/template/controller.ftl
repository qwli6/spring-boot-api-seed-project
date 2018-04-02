package ${packageName}.web.controller;


import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import java.util.List;
import ${packageName}.model.${className};
import ${packageName}.service.${className}Service;

/**
*  Author: ${author}
*  Email: ${email}
*  Date: ${.now?string("yyyy-MM-dd")}
*  Company: ${company}
*/

@Controller
@RequestMapping("/${className?lower_case}")
public class ${className}Controller {

    @Resource(name = "${className?uncap_first}Service")
    private ${className}Service ${className?uncap_first}Service;

    /**
     * 获取 ${className?lower_case} 列表
     * @return ${className?lower_case} 列表
     **/
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String find${className}List(Model model){
        List<${className}> ${className?lower_case}List = ${className?uncap_first}Service.findAll();
        //补充其他逻辑
        if(${className?lower_case}List != null && ${className?lower_case}List.size()>0){
            model.addAttribute("${className?lower_case}List", ${className?lower_case}List);
        }
        return "${className?lower_case}_list";
    }

    /**
     * 去新增/修改 ${className} 页面
     * @return 页面
     **/
    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public String save${className}(${className} ${className?uncap_first}, Model model){

        //修改 ${className}
        if(${className?uncap_first} != null){
            model.addAttribute("${className?lower_case}", ${className?uncap_first});
            return "update_${className?lower_case}";
        //新增 ${className}
        }else{
            return "add_${className?lower_case}";
        }
    }

    /**
    *  保存 ${className} 页面
    * @return 页面
    **/
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save${className}(${className} ${className?uncap_first}){
        ${className?uncap_first}Service.save${className}(${className?uncap_first});
        return "redirect:/${className?lower_case}/list";
    }

    /**
    * 保存修改
    * @return 页面
    **/
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update${className}(${className} ${className?lower_case},Model model){
        //根据用户 id 查找用户
        int updateCount = ${className?uncap_first}Service.update${className}(${className?lower_case});
        if(updateCount == 1){
            return "redirect:/${className?lower_case}/list";
        }
        return null;
    }

    /**
     * 分页，无条件分页
     * @return ${className?uncap_first}List
     **/
    @RequestMapping(value = "/list/{page}",method = RequestMethod.GET)
    public String find${className}ListByPage(@PathVariable("page") Integer page,
                                             @RequestParam(defaultValue = "0", required = true) Integer size){

        return null;
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String find${className}ListByVo(QueryVo queryVo){

        return null;
    }
}