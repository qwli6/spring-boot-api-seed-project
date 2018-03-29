package ${packageName}.service.impl;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import ${packageName}.model.${className};
import ${packageName}.service.${className}Service;
import ${packageName}.mapper.${className}Mapper;

/**
*  Author: ${author}
*  Email: ${email}
*  Date: ${.now?string("yyyy-MM-dd")}
*  Company: ${company}
*/
@Service
@Transactional
public class ${className}ServiceImpl implements ${className}Service {

    @Resource
    private ${className}Mapper ${className?uncap_first}Mapper;

    @Override
    public void save${className}(${className} ${className?uncap_first}){
        ${className?uncap_first}Mapper.save${className}(${className?uncap_first});
    }

    @Override
    public void delete${className}ById(@Param("id") Integer id){
        ${className?uncap_first}Mapper.delete${className}ById(id);
    }

    @Override
    public void edit${className}ById(${className} ${className?uncap_first}){
        ${className?uncap_first}Mapper.edit${className}ById(${className?uncap_first});
    }

    @Override
    public ${className} find${className}ById(@Param("id") Integer id){
        return ${className?uncap_first}Mapper.find${className}ById(id);
    }

    @Override
    public List<${className}> find${className}sByConditions(){
        return null;
    }

    @Override
    public List<${className}> find${className}sNoConditions(){
        return ${className?uncap_first}Mapper.find${className}sNoConditions();
    }

    @Override
    public List<${className}> find${className}sByPage(){
        <#--${pageName} pageName = new ${pageName}();-->
        <#--pageName.set-->
        return null;
    }
}