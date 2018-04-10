package ${packageName}.service.impl;

import ${packageName}.core.AbstractService;
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
*
*  这里只需要实现不同部分的逻辑即可，
*  公共部分的逻辑已经全部在 AbstractService 中实现了。
*/
@Service("${className?uncap_first}Service")
@Transactional
public class ${className}ServiceImpl extends AbstractService<${className}> implements ${className}Service {

    @Resource(name = "${className?uncap_first}Mapper")
    private ${className}Mapper ${className?uncap_first}Mapper;

}