package com.company.project;

import freemarker.template.Configuration;
import freemarker.template.Template;
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
public class FreemarkerUtils {

    private static Logger logger = LoggerFactory.getLogger(FreemarkerUtils.class);
    private static final String PROJECT_PATH = System.getProperty("user.dir");
    private static final String JAVA_PATH = "/src/main/java/";
    private static final String RESOURCES_PATH = "/src/main/resources";
    private static final String TEMPLATE_FILE_PATH = PROJECT_PATH + "/src/test/resources/template/";
    private static final Configuration configuration;
    private static final String MODEL_PACKAGE_SUFFIX = ".model";
    private static final String MODEL_CLASS_SUFFIX = ".java";
    private static final String MAPPER_PACKAGE_SUFFIX = ".mapper";
    private static final String MAPPER_CLASS_SUFFIX = "Mapper.java";
    private static final String MAPPER_XML_DIR_SUFFIX = "/mapper";
    private static final String MAPPER_XML_FILE_SUFFIX = "Mapper.xml";
    private static final String SERVICE_PACKAGE_SUFFIX = ".service";
    private static final String SERVICE_CLASS_SUFFIX = "Service.java";
    private static final String SERVICE_IMPL_PACKAGE_SUFFIX = ".service.impl";
    private static final String SERVICE_IMPL_CLASS_SUFFIX = "ServiceImpl.java";
    private static final String CONTROLLER_PACKAGE_SUFFIX = ".controller";
    private static final String CONTROLLER_CLASS_SUFFIX = "Controller.java";
    private static final String TEMPLATE_PAGE_BEAN = "page_bean.ftl";
    private static final String TEMPLATE_MODEL = "model.ftl";
    private static final String TEMPLATE_MAPPER = "mapper.ftl";
    private static final String TEMPLATE_SERVICE = "service.ftl";
    private static final String TEMPLATE_SERVICE_IMPL = "service_impl.ftl";
    private static final String TEMPLATE_CONTROLLER = "controller.ftl";
    private static final String TEMPLATE_REST_CONTROLLER = "rest_controller.ftl";
    private static final String TEMPLATE_MAPPER_XML = "mapper_xml.ftl";


    static{
        configuration = new Configuration(Configuration.getVersion());
        //设置模板所在的路径
        try {
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_FILE_PATH));
            configuration.setDefaultEncoding("UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 生成 Model 和 Mapper 类
     */
    public static void genModelAndMapper(Map<String, Object> root) throws Exception {
        logger.info("========> 开始生成 " + root.get(CodeGenerator.CLASS_NAME) +".java 文件.......");
        Template template = configuration.getTemplate(TEMPLATE_MODEL);
        String path =PathUtils.packageNameToPath(root.get(CodeGenerator.BASE_PACKAGE_KEY) + MODEL_PACKAGE_SUFFIX);
        File dir = new File(PROJECT_PATH + JAVA_PATH + path);
        if(!dir.exists()){
            boolean isSuccess = dir.mkdirs();
            if(isSuccess){
                logger.info("目录不存在，文件创建成功");
            }
        }
        Writer out = new FileWriter(new File(dir, root.get(CodeGenerator.CLASS_NAME) + MODEL_CLASS_SUFFIX));
        template.process(root, out);
        out.flush();
        logger.info("========>  " + root.get(CodeGenerator.CLASS_NAME) +".java 文件生成完成.......");

        logger.info("========> 开始生成 " + root.get(CodeGenerator.CLASS_NAME) +"Mapper.java 文件....... ");
        template = configuration.getTemplate(TEMPLATE_MAPPER);
        String mapperPath = PathUtils.packageNameToPath(root.get(CodeGenerator.BASE_PACKAGE_KEY) + MAPPER_PACKAGE_SUFFIX);
        File mapperDir = new File(PROJECT_PATH + JAVA_PATH + mapperPath);
        if(!mapperDir.exists()){
           mapperDir.delete();
            boolean mkdirs = mapperDir.mkdirs();
            if(mkdirs){
                logger.info("创建 mapper 目录成功");
            }
        }
        out = new FileWriter(new File(mapperDir, root.get(CodeGenerator.CLASS_NAME) + MAPPER_CLASS_SUFFIX));
        template.process(root, out);
        out.close();
        logger.info("========> " + root.get(CodeGenerator.CLASS_NAME) +"Mapper.java 文件生成完成.......");
    }




    public static void genServiceAndImpl(Map<String, Object> root) throws Exception{
        logger.info("========> 开始生成 " + root.get(CodeGenerator.CLASS_NAME) +"Service.java 文件....... ");
        Template template = configuration.getTemplate(TEMPLATE_SERVICE);
        String servicePath = PathUtils.packageNameToPath(root.get(CodeGenerator.BASE_PACKAGE_KEY) + SERVICE_PACKAGE_SUFFIX);
        File dir = new File(PROJECT_PATH + JAVA_PATH + servicePath);
        if(!dir.exists()){
            boolean isSuccess = dir.mkdirs();
            if(isSuccess){
                logger.info("目录不存在，创建目录成功");
            }
        }
        Writer out = new FileWriter(new File(dir, root.get(CodeGenerator.CLASS_NAME) + SERVICE_CLASS_SUFFIX));
        template.process(root, out);
        out.flush();
        logger.info("========>  " + root.get(CodeGenerator.CLASS_NAME) +"Service.java 文件生成完成.......");
        logger.info("========> 开始生成 " + root.get(CodeGenerator.CLASS_NAME) +"ServiceImpl.java 文件....... ");
        template = configuration.getTemplate(TEMPLATE_SERVICE_IMPL);
        String implPath = PathUtils.packageNameToPath(root.get(CodeGenerator.BASE_PACKAGE_KEY) + SERVICE_IMPL_PACKAGE_SUFFIX);
        dir = new File(PROJECT_PATH + JAVA_PATH + implPath);
        if(!dir.exists()){
            boolean isSuccess = dir.mkdirs();
            if(isSuccess){
                logger.info("目录不存在，创建目录成功");
            }
        }
        out = new FileWriter(new File(dir, root.get(CodeGenerator.CLASS_NAME) + SERVICE_IMPL_CLASS_SUFFIX));
        template.process(root, out);
        out.close();
        logger.info("========>  " + root.get(CodeGenerator.CLASS_NAME) +"ServiceImpl.java 文件生成完成.......");
    }


    /**
     * 生成 Controller
     * @param root
     * @param isRest
     * @throws Exception
     */
    public static void genController(Map<String, Object> root, boolean isRest) throws Exception{
        Template template;
        File dir;
        Writer out;
        if(isRest) {
            logger.info("========> isRest: " + isRest + "！=======>" +"用户要求生成带有 Restful 风格的 RestController 控制器.......");
            logger.info("========> 开始生成 " + root.get(CodeGenerator.CLASS_NAME) +"Controller.java 文件....... ");
            template = configuration.getTemplate(TEMPLATE_REST_CONTROLLER);
        }else{
            logger.info("========> isRest: " + isRest + "！=======>" +"用户要求生成不带 Restful 风格的 Controller 控制器.......");
            logger.info("========> 开始生成 " + root.get(CodeGenerator.CLASS_NAME) +"Controller.java 文件....... ");
            template = configuration.getTemplate(TEMPLATE_CONTROLLER);
        }
        String controllerPath = PathUtils.packageNameToPath(root.get(CodeGenerator.BASE_PACKAGE_KEY) + CONTROLLER_PACKAGE_SUFFIX);
        dir = new File(PROJECT_PATH + JAVA_PATH + controllerPath);
        if(!dir.exists()){
            boolean isSuccess = dir.mkdirs();
            if (isSuccess) {
                logger.info("目录不存在，创建目录成功");
            }
        }
        out = new FileWriter(new File(dir, root.get(CodeGenerator.CLASS_NAME) + CONTROLLER_CLASS_SUFFIX));
        template.process(root, out);
        out.close();
        logger.info("========>  " + root.get(CodeGenerator.CLASS_NAME) +"Controller.java 文件生成完成.......");
    }


    /**
     * 生成 mapper 映射文件
     * @param root
     * @throws Exception
     */
    public static void genMapperXml(Map<String, Object> root) throws Exception{
        logger.info("========> 开始生成 " + root.get(CodeGenerator.CLASS_NAME) +"Mapper.xml 文件....... ");
        Template template = configuration.getTemplate(TEMPLATE_MAPPER_XML);
        File dir = new File(PROJECT_PATH + RESOURCES_PATH + MAPPER_XML_DIR_SUFFIX);
        if(!dir.exists()){
            boolean mkdirs = dir.mkdirs();
            if(mkdirs){
                logger.info("mapper 目录不存在，创建 mapper 目录成功！");
            }
        }
        Writer out = new FileWriter(new File(dir, root.get(CodeGenerator.CLASS_NAME) + MAPPER_XML_FILE_SUFFIX));
        template.process(root, out);
        out.close();
        logger.info("========>  " + root.get(CodeGenerator.CLASS_NAME) +"Mapper.xml 文件生成完成.......");
    }


    /**
     * 生成分页实体类
     * @param root
     * @throws Exception
     */
    public static void genPageBean(Map<String, Object> root) throws Exception{
        Template template = configuration.getTemplate(TEMPLATE_PAGE_BEAN);
        String path =PathUtils.packageNameToPath(root.get(CodeGenerator.BASE_PACKAGE_KEY) + MODEL_PACKAGE_SUFFIX);
        File dir = new File(PROJECT_PATH + JAVA_PATH + path);
        if(!dir.exists()){
            boolean mkdirs = dir.mkdirs();
            if(mkdirs){
                logger.info("model  目录不存在，创建 model 目录成功！");
            }
        }
        Writer writer = new FileWriter(new File(dir, "PageBean" + MODEL_CLASS_SUFFIX));
        root.put(CodeGenerator.CLASS_NAME, "PageBean");
        Map<String,Object> varMaps = new HashMap<>();
        varMaps.put("pageSize", "int");
        varMaps.put("currentPage", "int");
        varMaps.put("totalPage", "int");
        varMaps.put("totalResult", "int");
        varMaps.put("dataList", "List<T>");
        root.put("varMaps", varMaps);
        template.process(root,writer);
        writer.close();
    }
}
