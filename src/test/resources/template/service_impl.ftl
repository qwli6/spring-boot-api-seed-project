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
@Service("${className?uncap_first}Service")
@Transactional
public class ${className}ServiceImpl implements ${className}Service {

    @Resource(name = "${className?uncap_first}Mapper")
    private ${className}Mapper ${className?uncap_first}Mapper;

    @Override
    public void save${className}(${className} ${className?uncap_first}){
        ${className?uncap_first}Mapper.save${className}(${className?uncap_first});
    }

    @Override
    public List<${className}> find${className}List(){
        return ${className?uncap_first}Mapper.find${className}List();
    }

    @Override
    public Integer selectCount(){
        return ${className?uncap_first}Mapper.selectCount();
    }

<#list dataMap as key>
    <#if key_index == 0>
    @Override
    public void delete${className}By${key.columnJavaName?cap_first}(${key.columnType} ${key.columnJavaName}){
        ${className?uncap_first}Mapper.delete${className}By${key.columnJavaName?cap_first}(${key.columnJavaName});
    }

    @Override
    public void deleteBatch(List<${key.columnType}> ids){
        ${className?uncap_first}Mapper.deleteBatch(ids);
    }

    @Override
    public ${className} find${className}By${key.columnJavaName?cap_first}(${key.columnType} ${key.columnJavaName}){
        return ${className?uncap_first}Mapper.find${className}By${key.columnJavaName?cap_first}(${key.columnJavaName});
    }
    </#if>
</#list>

    @Override
    public void edit${className}(${className} ${className?lower_case}){
        ${className?uncap_first}Mapper.edit${className}(${className?lower_case});
    }
}