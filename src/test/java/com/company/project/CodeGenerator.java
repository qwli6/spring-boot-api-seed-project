package com.company.project;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.DatabaseMetaData;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private static final String TABLE = "TABLE";
    private static final Integer TABLE_NAME_INDEX = 3;
    private static final String EXECUTABLE_SQL = "SELECT * FROM ";
    private static final String CONTEXT_BEGINNING_DELIMITER = "`";
    private static final String CONTEXT_ENDING_DELIMITER = "`";

    private static final String AUTHOR_KEY = "author";
    private static final String AUTHOR_VALUE = "CodeGenerator";
    public static final String BASE_PACKAGE_KEY = "packageName";
    private static final String BASE_TARGET_PACKAGE = "com.company.project";
    public static final String CLASS_NAME = "className";
    private static final String EMAIL_KEY = "email";
    private static final String COMPANY_KEY = "company";
    private static final String EMAIL_VALUE = "selfassu@gmail.com";
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
                    null, new String[]{TABLE});
            while (resultSet.next()){
                tableName.add(resultSet.getString(TABLE_NAME_INDEX));
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
     * 获取数据库列名称对应的名称和属性
     */
    private static Map<String, Object> getColumnNameAndType(String tableName){
        Map<String, Object> nameAndTypeMap = new HashMap<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        String sql = EXECUTABLE_SQL + CONTEXT_BEGINNING_DELIMITER + tableName + CONTEXT_ENDING_DELIMITER;

        String tempValue = null;

        try {
            statement = connection.prepareStatement(sql);
            ResultSetMetaData metaData = statement.getMetaData();
            //表列数
            int columnCount = metaData.getColumnCount();
            for (int i = 0; i < columnCount; i++) {
                String columnType = metaData.getColumnTypeName(i + 1);
//                String columnName = tableNameToLowerCamel(metaData.getColumnName(i + 1));
                String columnName = metaData.getColumnName(i + 1);
                switch (columnType){
                    case "VARCHAR":
                        tempValue = "String";
                        break;
                    case "INT":
                    case "INTEGER":
                    case "SMALLINT":
                        tempValue = "Integer";
                        break;
                    case "FLOAT":
                        tempValue = "Float";
                        break;
                    case "DOUBLE":
                        tempValue = "Double";
                        break;
                    case "DATE":
                    case "DATETIME":
                    case "TIMESTAMP":
                    case "TIME":
                        tempValue = "Date";
                        break;
                    case "TINYINT":
                    case "BOOL":
                    case "BOOLEAN":
                        tempValue = "Short";
                        break;
                    case "BIGINT":
                    case "MEDIUMINT":
                        tempValue = "Long";
                        break;
                }
                nameAndTypeMap.put(columnName, tempValue);
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
        root.put(CLASS_NAME,PathUtils.tableNameToUpperCamel(tableName));
        root.put(AUTHOR_KEY, AUTHOR_VALUE);
        root.put(EMAIL_KEY, EMAIL_VALUE);
        root.put(COMPANY_KEY, COMPANY_VALUE);
        root.put(BASE_PACKAGE_KEY, BASE_TARGET_PACKAGE);

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
