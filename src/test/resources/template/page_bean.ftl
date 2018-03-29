package ${packageName}.model.bo;

import java.io.Serializable;
import java.util.List;


/**
*  Author: ${author}
*  Email: ${email}
*  Date: ${.now?string("yyyy-MM-dd")}
*  Company: ${company}
*/
public class ${className}<T> implements Serializable {

    <#list varMaps?keys as key>
        private ${varMaps["${key}"]} ${key};
    </#list>

<#list varMaps?keys as key>

    public void set${key?cap_first}(${varMaps["${key}"]} ${key}){
        this.${key} = ${key};
    }
    public ${varMaps["${key}"]} get${key?cap_first}(){
        return this.${key};
    }
</#list>
}