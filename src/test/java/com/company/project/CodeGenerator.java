package com.company.project;

import com.company.project.core.ProjectConstants;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.DatabaseMetaData;
import freemarker.template.Template;
import org.apache.ibatis.annotations.Case;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liqiwen on 2017/6/30.
 * 代码生成器，由 mybatis 的代码生成器改写得来
 * 生成 mapper，service，controller，entity 等类
 * 简化开发
 *
 * 原理：利用 freemarker 模板生成 java 文件
 */
public class CodeGenerator {
    private static Logger logger = LoggerFactory.getLogger(CodeGenerator.class);

    //数据库配置
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mybatis?useSSL=false";
    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "student";

    //设置成你自己的信息
    //作者名称
    private static final String AUTHOR_VALUE = "CodeGenerator";
    //生成的类所在的基础包名
    //mapper 生成包名 com.company.project.mapper
    //model 生成包名 com.company.project.model
    //service 生成包名 com.company.project.service
    //serviceImpl 生成包名 com.company.project.service.impl
    //controller 生成包名 com.company.project.web.controller
    private static final String BASE_TARGET_PACKAGE = "com.company.project";
    //作者邮箱
    private static final String EMAIL_VALUE = "selfassu@gmail.com";
    //作者公司
    private static final String COMPANY_VALUE = "com.xxx.yyy";


    static {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            logger.error("不能加载数据库驱动！异常信息：" + e.getMessage());
        }
    }

    /**
     * 获取数据库连接
     * @return 数据库连接
     */
    private static Connection getConnection() throws SQLException{
        return (Connection) DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }

    /**
     * 关闭数据库连接
     * @param connection 关闭连接
     */
    private static void closeConnection(Connection connection) throws Exception {
        if(connection != null) {
            connection.close();
        }
    }

    /**
     * 获取数据库表名
     * @return 数据库表名集合
     */
    private static List<String> getTableName() throws Exception{
        List<String> tableName = new ArrayList<>();
        Connection connection = getConnection();
        DatabaseMetaData db = (DatabaseMetaData) connection.getMetaData();
        ResultSet resultSet = db.getTables(null, null,
                null, new String[]{ProjectConstants.TABLE});
        while (resultSet.next()){
            tableName.add(resultSet.getString(ProjectConstants.TABLE_NAME_INDEX));
        }
        return tableName;
    }


    /**
     * mysql 列类型转 java 类型
     * @param columnType 列类型
     * @return java 类型
     * jdk1.8 以上 switch 才支持字符串，如果 jdk 版本小于 1.8，请自行更改成 if else 的方式
     */
    private static String mysqlColumnType2JavaType(String columnType){
        String javaType;
        switch (columnType){
            case ProjectConstants.MYSQL_TYPE_VARCHAR:
            case ProjectConstants.MYSQL_TYPE_CHAR:
                javaType = ProjectConstants.JAVA_TYPE_STRING;
                break;
            case ProjectConstants.MYSQL_TYPE_INTEGER:
            case ProjectConstants.MYSQL_TYPE_INT:
            case ProjectConstants.MYSQL_TYPE_SMALLINT:
                javaType = ProjectConstants.JAVA_TYPE_INTEGER;
                break;
            case ProjectConstants.MYSQL_TYPE_FLOAT:
                javaType = ProjectConstants.JAVA_TYPE_FLOAT;
                break;
            case ProjectConstants.MYSQL_TYPE_DOUBLE:
                javaType = ProjectConstants.JAVA_TYPE_DOUBLE;
                break;
            case ProjectConstants.MYSQL_TYPE_DATE:
            case ProjectConstants.MYSQL_TYPE_TIME:
            case ProjectConstants.MYSQL_TYPE_TIMESTAMP:
            case ProjectConstants.MYSQL_TYPE_DATETIME:
                javaType = ProjectConstants.JAVA_TYPE_DATE;
                break;
            case ProjectConstants.MYSQL_TYPE_TINYINT:
            case ProjectConstants.MYSQL_TYPE_BOOL:
            case ProjectConstants.MYSQL_TYPE_BOOLEAN:
                javaType = ProjectConstants.JAVA_TYPE_SHORT;
                break;
            case ProjectConstants.MYSQL_TYPE_BIGINT:
            case ProjectConstants.MYSQL_TYPE_MEDIUMINT:
                javaType = ProjectConstants.JAVA_TYPE_LONG;
                break;
                default:
                    javaType = ProjectConstants.JAVA_TYPE_STRING;
                    break;
        }
        return javaType;
    }


    /**
     * 获取数据库列名称对应的
     * 名称
     * 属性
     * 注释
     * 转驼峰命名的java属性名称
     */
    private static List<ColumnClass> getColumnNameAndType(String tableName) throws Exception{
        List<ColumnClass> columnClassList = new ArrayList<>();
        Connection connection = getConnection();
        //给执行的 sql 中表名加上 `表名`
        //不加有些表会报错，比如 order 表
        // select * from order;  这样写执行可能会报错
        String sql = ProjectConstants.EXECUTABLE_SQL + "`" + tableName + "`";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSetMetaData metaData = statement.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            ColumnClass columnClass = new ColumnClass();
            String columnType = metaData.getColumnTypeName(i + 1);
            columnClass.setColumnType(mysqlColumnType2JavaType(columnType));
            String columnJavaName = PathUtils.tableNameToLowerCamel(metaData.getColumnName(i + 1));
            columnClass.setColumnJavaName(columnJavaName);
            String columnName = metaData.getColumnName(i + 1);
            columnClass.setColumnName(columnName);
            columnClassList.add(columnClass);
        }
        return columnClassList;
    }

    /**
     * 是否生成 restful 风格的 controller
     * @param isRest true 表示只提供 api，不提供界面
     *               false 不提供 api，提供界面
     * 只有为 true 的时候才会生成页面模板
     * 为 true 的时候只生成接口
     */
    private void generator(boolean isRest) throws Exception{
        List<String> tableNames = getTableName();
        for (int i = 0; i < tableNames.size(); i++) {
            try {
                generateModelFile(tableNames.get(i));
                generateMapperFile(tableNames.get(i));
                generateMapperXmlFile(tableNames.get(i));
                generateServiceAndImplFile(tableNames.get(i));
                generateController(tableNames.get(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void generateController(String tableName) throws Exception{
        Map<String, Object> dataMap = new HashMap<>();
        String template = ProjectConstants.TEMPLATE_REST_CONTROLLER;
        String packageSuffix = ProjectConstants.CONTROLLER_PACKAGE_SUFFIX;
        String classSuffix = ProjectConstants.CONTROLLER_CLASS_SUFFIX;
        List<ColumnClass> columnClassList = getColumnNameAndType(tableName);
        dataMap.put(ProjectConstants.CLASS_NAME, PathUtils.tableNameToUpperCamel(tableName));
        dataMap.put("dataMap", columnClassList);
        generateFileByTemplate(template, packageSuffix, classSuffix, dataMap);
    }


    private void generateServiceAndImplFile(String tableName) throws Exception{
        Map<String, Object> dataMap = new HashMap<>();
        String template = ProjectConstants.TEMPLATE_SERVICE;
        String packageSuffix = ProjectConstants.SERVICE_PACKAGE_SUFFIX;
        String classSuffix = ProjectConstants.SERVICE_CLASS_SUFFIX;
        List<ColumnClass> columnClassList = getColumnNameAndType(tableName);
        dataMap.put(ProjectConstants.CLASS_NAME, PathUtils.tableNameToUpperCamel(tableName));
        dataMap.put("dataMap", columnClassList);
        generateFileByTemplate(template, packageSuffix, classSuffix, dataMap);
        template = ProjectConstants.TEMPLATE_SERVICE_IMPL;
        packageSuffix = ProjectConstants.SERVICE_IMPL_PACKAGE_SUFFIX;
        classSuffix = ProjectConstants.SERVICE_IMPL_CLASS_SUFFIX;
        generateFileByTemplate(template, packageSuffix, classSuffix, dataMap);
    }

    private void generateMapperXmlFile(String tableName) throws Exception{
        Map<String, Object> dataMap = new HashMap<>();
        String template = ProjectConstants.TEMPLATE_MAPPER_XML;
        String packageSuffix = ProjectConstants.MAPPER_XML_DIR_SUFFIX;
        String classSuffix = ProjectConstants.MAPPER_XML_FILE_SUFFIX;
        List<ColumnClass> columnClassList = getColumnNameAndType(tableName);
        dataMap.put(ProjectConstants.CLASS_NAME, PathUtils.tableNameToUpperCamel(tableName));
        dataMap.put("dataMap", columnClassList);
        generateFileByTemplate(template, packageSuffix, classSuffix, dataMap);
    }


    private void generateMapperFile(String tableName) throws Exception{
        Map<String, Object> dataMap = new HashMap<>();
        String template = ProjectConstants.TEMPLATE_MAPPER;
        String packageSuffix = ProjectConstants.MAPPER_PACKAGE_SUFFIX;
        String classSuffix = ProjectConstants.MAPPER_CLASS_SUFFIX;
        List<ColumnClass> columnClassList = getColumnNameAndType(tableName);
        dataMap.put(ProjectConstants.CLASS_NAME, PathUtils.tableNameToUpperCamel(tableName));
        dataMap.put("dataMap", columnClassList);
        generateFileByTemplate(template, packageSuffix, classSuffix, dataMap);
    }


    private void generateModelFile(String tableName) throws Exception{
        Map<String, Object> dataMap = new HashMap<>();
        String template = ProjectConstants.TEMPLATE_MODEL;
        String packageSuffix = ProjectConstants.MODEL_PACKAGE_SUFFIX;
        String classSuffix = ProjectConstants.MODEL_CLASS_SUFFIX;
        List<ColumnClass> columnClassList = getColumnNameAndType(tableName);
        dataMap.put(ProjectConstants.CLASS_NAME, PathUtils.tableNameToUpperCamel(tableName));
        dataMap.put("dataMap", columnClassList);
        generateFileByTemplate(template, packageSuffix, classSuffix, dataMap);
    }


    private void generateFileByTemplate(String templateName,String packageSuffix, String classSuffix, Map<String, Object> dataMap) throws Exception {
        Template template = FreemarkerUtils.loadTemplate(templateName);
        dataMap.put(ProjectConstants.AUTHOR_KEY, AUTHOR_VALUE);
        dataMap.put(ProjectConstants.EMAIL_KEY, EMAIL_VALUE);
        dataMap.put(ProjectConstants.COMPANY_KEY, COMPANY_VALUE);
        dataMap.put(ProjectConstants.BASE_PACKAGE_KEY, BASE_TARGET_PACKAGE);
        String path =PathUtils.packageNameToPath(dataMap.get(ProjectConstants.BASE_PACKAGE_KEY) + packageSuffix);
        File file;
        if(classSuffix.equals(ProjectConstants.MAPPER_XML_FILE_SUFFIX)) {
            file = new File(ProjectConstants.PROJECT_PATH + ProjectConstants.RESOURCES_PATH + packageSuffix);
        }else {
            file = new File(ProjectConstants.PROJECT_PATH + ProjectConstants.JAVA_PATH + path);
        }
        if(!file.exists()){
            file.mkdirs();
        }else{
            file.delete();
            file.mkdirs();
        }
        Writer out = new FileWriter(new File(file, dataMap.get(ProjectConstants.CLASS_NAME) + classSuffix));
        template.process(dataMap, out);
        out.flush();
    }


    public static void main(String[] args) throws Exception {
        CodeGenerator codeGenerator = new CodeGenerator();
        codeGenerator.generator(true);
    }
}
