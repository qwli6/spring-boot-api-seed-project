package ${packageName}.model;

import java.io.Serializable;


/**
*  Author: ${author}
*  Email: ${email}
*  Date: ${.now?string("yyyy-MM-dd")}
*  Company: ${company}
*/
public class ${className} implements Serializable {

<#list dataMap as property>
    private ${property.columnType} ${property.columnJavaName};
</#list>

<#list dataMap as getAndSet>
    public void set${getAndSet.columnJavaName?cap_first}(${getAndSet.columnType} ${getAndSet.columnJavaName}){
        this.${getAndSet.columnJavaName} = ${getAndSet.columnJavaName};
    }
    public ${getAndSet.columnType} get${getAndSet.columnJavaName?cap_first}(){
        return this.${getAndSet.columnJavaName};
    }
</#list>


}