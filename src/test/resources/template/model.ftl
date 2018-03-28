package ${packageName};


/**
*  Author: ${author}
*  Email: ${email}
*  Date: ${date}
*  Company: ${company}
*/
public class ${className} {

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