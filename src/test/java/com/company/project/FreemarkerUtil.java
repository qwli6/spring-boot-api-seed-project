package com.company.project;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 *
 * @author liqiwen
 * @date 2018-03-27 16:37
 */
public class FreemarkerUtil {

    private static Logger logger = LoggerFactory.getLogger(FreemarkerUtil.class);

    private static final String TARGET_PATH = "/Users/liqiwen/code/project/spring-boot-api-seed-project/src/main/java/";
    private static final String MODEL_PACKAGE_NAME ="com/company/project/entity";
    private static final String FTL_PATH = "/Users/liqiwen/code/project/spring-boot-api-" +
            "seed-project/src/test/resources/template/";

    private static final Configuration configuration;


    static{
        configuration = new Configuration(Configuration.getVersion());
        //设置模板所在的路径
        try {
            configuration.setDirectoryForTemplateLoading(new File(FTL_PATH));
            configuration.setDefaultEncoding("UTF-8");
        } catch (IOException e) {
            e.printStackTrace();

        }
    }


    /**
     * 生成 Model 类
     */
    public static void getModel(Map<String, Object> root, String className) throws IOException, TemplateException {
        logger.info("========> 开始生成 " + className +".java 文件.......");
        Template temp = configuration.getTemplate("entity.ftl");
        File dir = new File(TARGET_PATH + MODEL_PACKAGE_NAME);
        if(!dir.exists()){
            boolean isSuccess = dir.mkdirs();
            if(isSuccess){
                logger.info("目录不存在，文件创建成功");
            }
        }
        logger.info("========> 正在生成 " + className +".java 文件.......");
        Writer out = new FileWriter(new File(dir, className + ".java"));
        temp.process(root, out);
        out.close();
        logger.info("========> " + className +".java 文件已经生成完成.......");
    }






    public static void init() throws IOException, TemplateException {



        //创建一个 Configuration 对象
        Configuration configuration = new Configuration(Configuration.getVersion());
        //设置模板所在的路径
        configuration.setDirectoryForTemplateLoading(new File("/Users/liqiwen/code/project/spring-boot-api-" +
                "seed-project/src/test/resources/template/"));
        //设置模板的字符集
        configuration.setDefaultEncoding("UTF-8");

        Map<String, Object> root = new HashMap<>();

        root.put("packageName", "com.company.project.entity");
        root.put("className", "Person");
        root.put("author", "code generator");


        Template temp = configuration.getTemplate("entity.template");


        //创建一个数据集，可以是 List，可以是 map，推荐使用 map
        Map<String, String> varMap = new HashMap<>();
        varMap.put("id", "Long");
        varMap.put("name", "String");
        varMap.put("age", "Integer");
        varMap.put("hobby", "List<String>");
        root.put("varMap",varMap);
        File dir = new File(TARGET_PATH + MODEL_PACKAGE_NAME);
        if(!dir.exists()){
            boolean isSuccess = dir.mkdirs();
            if(isSuccess){
                logger.info("目录不存在，文件创建成功");
            }
        }
        //创建一个输出对象，指定输出文件的路径以及文件名称
        Writer out = new FileWriter(new File(dir, "Person.java"));
        temp.process(root, out);
        //关闭流
        out.close();


    }
}
