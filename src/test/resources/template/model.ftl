package ${packageName}.model;

import java.io.Serializable;


/**
*  Author: ${author}
*  Email: ${email}
*  Date: ${.now?string("yyyy-MM-dd")}
*  Company: ${company}
*/
public class ${className} implements Serializable {

<#list varMap?keys as key>
    private ${varMap["${key}"]} ${key};
</#list>

<#list varMap?keys as key>
    public void set${key?cap_first}(${varMap["${key}"]} ${key}){
        this.${key} = ${key};
    }
    public ${varMap["${key}"]} get${key?cap_first}(){
        return this.${key};
    }
</#list>
}