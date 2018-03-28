package com.company.project;

import com.google.common.base.CaseFormat;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    //工程相对路径
    private static final String PROJECT_PATH = System.getProperty("user.dir");
    //模板路径
    private static final String TEMPLATE_FILE_PATH = PROJECT_PATH + "/src/test/resources/template";
    //java 文件路径
    private static final String JAVA_PATH = "/src/main/java";
    //资源文件路径
    private static final String RESOURCES_PATH = "/src/main/resources";

    private static final String PACKAGE_PATH_SERVICE = null;
    private static final String PACKAGE_PATH_SERVICE_IMPL = "";
    private static final String PACKAGE_PATH_CONTROLLER = "";


    private static final String TABLE = "TABLE";
    private static final Integer TABLE_NAME_INDEX = 3;
    private static final String EXECUTABLE_SQL = "SELECT * FROM ";
    private static final String CONTEXT_BEGINNING_DELIMITER = "`";
    private static final String CONTEXT_ENDING_DELIMITER = "`";

    private static final String AUTHOR = "author";
    private static final String AUTHOR_NAME = "CodeGenerator";
    private static final String CREATE_DATE ="date";
    private static final String MODEL_PACKAGE = "packageName";
    private static final String CLASS_NAME = "className";
    private static final String COMPANY = "com.xxx.yyy";
    private static final String EMAIL_KEY = "email";
    private static final String EMAIL_VALUE = "小李@gmail.com";
    private static final String MODEL_TARGET_PACKAGE = "com.company.project.entity";


    static {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            logger.error("不能加载数据库驱动！异常信息：" + e.getMessage());
        }
    }

    /**
     * 包名转换成路径
     * @param packageName 包名
     * @return 路径
     */
    private static String packageNameToPath(String packageName){

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
    private static String tableNameToLowerCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableName.toLowerCase());
    }

    /**
     * 表名转换成大驼峰 比如 t_user --> TUser
     * @param tableName 表名
     * @return 大驼峰
     */
    private static String tableNameToUpperCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase());

    }
    /**
     * 表名转换成文件路径 比如 t_user --> /t/user
     * @param tableName 表名
     * @return 路径
     */
    private static String tableNameToPath(String tableName) {
        tableName = tableName.toLowerCase();//兼容使用大写的表名
        return "/" + (tableName.contains("_") ? tableName.replaceAll("_", "/") : tableName);
    }


    /**
     * 模型名称转换成路径 例如 User --> /user
     * @param modelName 实体类名称
     * @return 路径
     */
    private static String modelNameToPath(String modelName) {
        String tableName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, modelName);
        return tableNameToPath(tableName);
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
     * 获取数据库列名称
     */
    private static List<String> getColumnName(String tableName){
        List<String> columnNames = new ArrayList<>();
        PreparedStatement statement = null;
        Connection connection = getConnection();
        String sql = EXECUTABLE_SQL + tableName;
        try {
             statement = connection.prepareStatement(sql);
            ResultSetMetaData resultSetMetaData = statement.getMetaData();

            int columnCount = resultSetMetaData.getColumnCount();
            for (int i = 0; i < columnCount; i++) {
                String columnName = resultSetMetaData.getColumnName(i + 1);
                columnNames.add(tableNameToLowerCamel(columnName));
            }
        } catch (SQLException e) {
            logger.error("执行 SQL 失败！异常信息：" + e.getMessage());
        }finally {
            if(statement != null){
                try {
                    statement.close();
                    closeConnection(connection);
                } catch (SQLException e) {
                    logger.error("关闭失败！异常信息：" + e.getMessage());
                }
            }
        }
        logger.info("表 " + tableName + " 的字段分别为：" + columnNames);
        return columnNames;
    }


    /**
     * 获取数据库列名称对应的名称和属性
     */
    private static Map<String, Object> getColumnType(String tableName){
//        List<String> columnTypes = new ArrayList<>();

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
                String columnName = tableNameToLowerCamel(metaData.getColumnName(i + 1));
                //INT VARCHAR VARCHAR INT
                //另外的 BIT 属性不支持
                switch (columnType){
                    case "VARCHAR":
                        tempValue = "String";
//                        columnTypes.add("String");
                        break;
                    case "INT":
                    case "INTEGER":
                    case "SMALLINT":
                        tempValue = "Integer";
//                        columnTypes.add("Integer");
                        break;
                    case "FLOAT":
                        tempValue = "Float";
//                        columnTypes.add("Float");
                        break;
                    case "DOUBLE":
                        tempValue = "Double";
//                        columnTypes.add("Double");
                        break;
                    case "DATE":
                    case "DATETIME":
                    case "TIMESTAMP":
                    case "TIME":
                        tempValue = "Date";
//                        columnTypes.add("Date");
                        break;
                    case "TINYINT":
                    case "BOOL":
                    case "BOOLEAN":
                        tempValue = "Short";
//                        columnTypes.add("Short");
                        break;
                    case "BIGINT":
                    case "MEDIUMINT":
                        tempValue = "Long";
//                        columnTypes.add("Long");
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

        logger.info("表 " + tableName +" 中对应的字段名称和类型为：" + nameAndTypeMap);
        return nameAndTypeMap;
    }






    private static void genModelAndMapper(String dbName, String modelName){
        Context context = new Context(ModelType.FLAT);
        context.setId("Potato");
        context.setTargetRuntime("MyBatis3Simple");
        context.addProperty(PropertyRegistry.CONTEXT_BEGINNING_DELIMITER,"`");
        context.addProperty(PropertyRegistry.CONTEXT_ENDING_DELIMITER, "`");

        JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
        jdbcConnectionConfiguration.setConnectionURL(DB_URL);
        jdbcConnectionConfiguration.setDriverClass(DB_DRIVER);
        jdbcConnectionConfiguration.setPassword(DB_PASSWORD);
        jdbcConnectionConfiguration.setUserId(DB_USERNAME);
        context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);
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

    public static void main(String[] args) throws Exception {
//        FreemarkerUtil.init();
        Map<String, Object> root;
        List<String> tableName = getTableName();
        for (int i = 0; i < tableName.size(); i++) {
            root = new HashMap<>();
            String fileName = tableName.get(i);
            Map<String, Object> nameAndMap = getColumnType(fileName);
            root.put(MODEL_PACKAGE,MODEL_TARGET_PACKAGE);
            root.put(CLASS_NAME, tableNameToUpperCamel(tableName.get(i)));
            root.put(AUTHOR,AUTHOR_NAME);
            root.put(CREATE_DATE, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
            root.put("company", COMPANY);
            root.put(EMAIL_KEY, EMAIL_VALUE);
            root.put("varMap", nameAndMap);
            FreemarkerUtil.getModel(root, tableNameToUpperCamel(tableName.get(i)));
        }
//        getColumnName("user");
//        getColumnType("user");
//        try {
//            CodeGenerator generateCode = new CodeGenerator();
//            generateCode.generator();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

}
