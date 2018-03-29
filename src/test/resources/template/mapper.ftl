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
@Component
public interface ${className}Mapper {

    /**
     *  保存 ${className}
     *  @return
     **/
    void save${className}(${className} ${className?uncap_first});

    /**
     *  按 id 删除 ${className}
     *  @return
     **/
    void delete${className}ById(@Param("id") Integer id);

    /**
     * 按 id 修改 ${className}
     *  @return
     **/
    void edit${className}ById(${className} ${className?uncap_first});

    /**
     *  根据 id 查找 ${className}
     *  @return ${className?lower_case}
     **/
    ${className} find${className}ById(@Param("id") Integer id);

    /**
     *  按条件获取 ${className} 列表
     *  @return ${className?lower_case} 列表
     **/
    List<${className}> find${className}sByConditions();

    /**
     *  无条件查询全部${className}列表
     *  @return ${className?lower_case} 列表
     **/
    List<${className}> find${className}sNoConditions();

    /**
     *  获取全部数量
     *  @return 总数
     **/
    Integer countNoConditions();

    /**
     *  按条件获取数量
     *  @return 总数
     **/
    Integer countByConditions();

    /**
     *  分页查找
     *  当前页的 ${className} 对象集合
     **/
    List<${className}> find${className}sByPage();
}