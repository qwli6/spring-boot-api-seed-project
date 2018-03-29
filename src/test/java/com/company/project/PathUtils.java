package com.company.project;

import com.google.common.base.CaseFormat;

/**
 * 描述:
 * 包名 <--> 路径 相互转换工具类
 *
 * @author liqiwen
 * @date 2018-03-29 09:58
 */
public class PathUtils {
    /**
     * 包名转换成路径
     * @param packageName 包名
     * @return 路径
     */
    public static String packageNameToPath(String packageName){

        if(packageName.contains(".")){
            return packageName.replace(".", "/");
        }else{
            return packageName;
        }
    }


    /**
     * 表名称转换成小驼峰 比如 t_user --> tUser
     * @param tableName 表名
     * @return 小驼峰
     */
    public static String tableNameToLowerCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableName.toLowerCase());
    }


    /**
     * 表名转换成大驼峰 比如 t_user --> TUser
     * @param tableName 表名
     * @return 大驼峰
     */
    public static String tableNameToUpperCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase());

    }


    /**
     * 表名转换成文件路径 比如 t_user --> /t/user
     * @param tableName 表名
     * @return 路径
     */
    public static String tableNameToPath(String tableName) {
        tableName = tableName.toLowerCase();//兼容使用大写的表名
        return "/" + (tableName.contains("_") ? tableName.replaceAll("_", "/") : tableName);
    }


    /**
     * 模型名称转换成路径 例如 User --> /user
     * @param modelName 实体类名称
     * @return 路径
     */
    public static String modelNameToPath(String modelName) {
        String tableName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, modelName);
        return tableNameToPath(tableName);
    }

}
