package ${packageName}.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import java.util.List;
import ${packageName}.model.${className};

/**
*  Author: ${author}
*  Email: ${email}
*  Date: ${.now?string("yyyy-MM-dd")}
*  Company: ${company}
*/
@Mapper
@Component("${className?uncap_first}Mapper")
public interface ${className}Mapper {

    /**
     *  保存 ${className}
     *  @return
     **/
    void save${className}(${className} ${className?uncap_first});

    /**
     * 获取列表
     * @return 列表
     **/
    List<${className}> find${className}List();

    /**
    *  获取全部数量
    *  @return 总数
    **/
    Integer selectCount();

<#list dataMap as key>
    <#if key_index == 0>
    /**
    *  删除
    **/
    void delete${className}By${key.columnJavaName?cap_first}(@Param("${key.columnJavaName}")${key.columnType} ${key.columnJavaName} );

    /**
    *  批量删除
    **/
    void deleteBatch(List<${key.columnType}> ids);

    /**
    *  根据主键获取
    *  @return ${className}
    **/
    ${className} find${className}By${key.columnJavaName?cap_first}(@Param("${key.columnJavaName}")${key.columnType} ${key.columnJavaName});
    </#if>
</#list>

    void edit${className}(${className} ${className?lower_case});
}