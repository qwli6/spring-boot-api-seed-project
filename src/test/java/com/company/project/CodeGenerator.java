package com.company.project;

import com.company.project.core.ProjectConstants;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.DatabaseMetaData;
import org.apache.ibatis.annotations.Case;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
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

    private static final String CONFIG_PATH = "/Users/liqiwen/code/project/" +
            "spring-boot-api-seed-project/src/test/" +
            "resource/generatorConfig.xml";

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
    private static Connection getConnection(){
        Connection connection = null;
        try {
            connection = (Connection) DriverManager
                    .getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            logger.error("不能获取数据库连接！异常信息：" + e.getMessage());
        }
        return connection;
    }

    /**
     * 关闭数据库连接
     * @param connection 关闭连接
     */
    private static void closeConnection(Connection connection){
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("数据库连接关闭失败！异常信息：" + e.getMessage());
            }
        }
    }

    /**
     * 获取数据库表名
     * @return 数据库表名集合
     */
    private static List<String> getTableName(){
        logger.info("开始获取数据库表名....");
        List<String> tableName = new ArrayList<>();
        Connection connection = getConnection();
        ResultSet resultSet = null;
        try {
            DatabaseMetaData db = (DatabaseMetaData) connection.getMetaData();
            resultSet = db.getTables(null, null,
                    null, new String[]{ProjectConstants.TABLE});
            while (resultSet.next()){
                tableName.add(resultSet.getString(ProjectConstants.TABLE_NAME_INDEX));
            }
        } catch (SQLException e) {
            logger.error("获取数据库表名称失败！异常信息：" + e.getMessage());
        } finally {
          if(resultSet != null){
              try {
                  resultSet.close();
                  closeConnection(connection);
              } catch (SQLException e) {
                  logger.error("结果集关闭失败！异常信息：" + e.getMessage());
              }
          }
        }
        logger.info("获取数据库表名称成功....");
        return tableName;
    }


    /**
     * mysql 列类型转 java 类型
     * @param columnType 列类型
     * @return java 类型
     * jdk1.8 以上 switch 才支持字符串，如果 jdk 版本小于 1.8，请自行更改成 if else 的方式
     */
    public static String mysqlColumnType2JavaType(String columnType){
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
     * oracle 列类型转换成 java 类型
     * @param columnType 列类型
     * @return java 类型
     *
     */
    public String oracleColumnType2JavaType(String columnType){

        return null;
    }


    /**
     * 获取数据库列名称对应的名称和属性
     */
    private static Map<String, Object> getColumnNameAndType(String tableName){
        Map<String, Object> nameAndTypeMap = new HashMap<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        //给执行的 sql 中表名加上 `表名`
        //不加有些表会报错，比如 order 表
        // select * from order;  这样写执行可能会报错
        String sql = ProjectConstants.EXECUTABLE_SQL +
                ProjectConstants.CONTEXT_BEGINNING_DELIMITER +
                tableName +
                ProjectConstants.CONTEXT_ENDING_DELIMITER;
        String tempValue = null;
        try {
            statement = connection.prepareStatement(sql);
            ResultSetMetaData metaData = statement.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 0; i < columnCount; i++) {
                String columnType = metaData.getColumnTypeName(i + 1);
//                String columnName = tableNameToLowerCamel(metaData.getColumnName(i + 1));
                String columnName = metaData.getColumnName(i + 1);
                nameAndTypeMap.put(columnName, mysqlColumnType2JavaType(columnType));
            }
        } catch (SQLException e) {
            logger.error("执行 SQL 失败！异常信息：" + e.getMessage());
        } finally {
            if(statement != null){
                try {
                    statement.close();
                    closeConnection(connection);
                } catch (SQLException e) {
                    logger.error("关闭数据库连接对象失败！异常信息：" + e.getMessage());
                }
            }
        }
        return nameAndTypeMap;
    }


    private void generator() throws Exception{
        List<String> warnings = new ArrayList<String>();
        System.out.println("====开始读取配置文件======");
        File configFile = new File(CONFIG_PATH);
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
                callback, warnings);
        myBatisGenerator.generate(null);
    }



    private static Map<String, Object> initData(String tableName){
        Map<String, Object> root = new HashMap<>();
        root.put(ProjectConstants.CLASS_NAME,PathUtils.tableNameToUpperCamel(tableName));
        root.put(ProjectConstants.AUTHOR_KEY, AUTHOR_VALUE);
        root.put(ProjectConstants.EMAIL_KEY, EMAIL_VALUE);
        root.put(ProjectConstants.COMPANY_KEY, COMPANY_VALUE);
        root.put(ProjectConstants.BASE_PACKAGE_KEY, BASE_TARGET_PACKAGE);

        Map<String, Object> colAndProMap = getColumnNameAndType(tableName);
        root.put("varMap", colAndProMap);

        return root;

    }


    /**
     * 是否生成 restful 风格的 controller
     * @param isRest true 表示只提供 api，不提供界面
     *               false 不提供 api，提供界面
     * 只有为 true 的时候才会生成页面模板
     * 为 true 的时候只生成接口
     */
    private static void generatorCode(boolean isRest){
        List<String> tableNames = getTableName();
        for (int i = 0; i < tableNames.size(); i++) {
            Map<String, Object> root = initData(tableNames.get(i));
            try {
                FreemarkerUtils.genModelAndMapper(root);
                FreemarkerUtils.genServiceAndImpl(root);
                FreemarkerUtils.genController(root, false);
                FreemarkerUtils.genMapperXml(root);
                FreemarkerUtils.genPageBean(root);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        generatorCode(false);
    }
}
