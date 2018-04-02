package com.company.project;

import com.company.project.core.ProjectConstants;
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
    private static final String TEMPLATE_FILE_PATH = ProjectConstants.PROJECT_PATH + "/src/test/resources/template/";
    private static final Configuration configuration;


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



    public static Template loadTemplate(String templateName) throws IOException{
        return configuration.getTemplate(templateName);
    }


    /**
     * 生成 Model 和 Mapper 类
     */
    public static void genModelAndMapper(Map<String, Object> root) throws Exception {
        logger.info("========> 开始生成 " + root.get(ProjectConstants.CLASS_NAME) +".java 文件.......");
        Template template = configuration.getTemplate(ProjectConstants.TEMPLATE_MODEL);
        String path =PathUtils.packageNameToPath(root.get(ProjectConstants.BASE_PACKAGE_KEY) + ProjectConstants.MODEL_PACKAGE_SUFFIX);
        File dir = new File(ProjectConstants.PROJECT_PATH + ProjectConstants.JAVA_PATH + path);
        if(!dir.exists()){
            boolean isSuccess = dir.mkdirs();
            if(isSuccess){
                logger.info("目录不存在，文件创建成功");
            }
        }
        Writer out = new FileWriter(new File(dir, root.get(ProjectConstants.CLASS_NAME) + ProjectConstants.MODEL_CLASS_SUFFIX));
        template.process(root, out);
        out.flush();
        logger.info("========>  " + root.get(ProjectConstants.CLASS_NAME) +".java 文件生成完成.......");

        logger.info("========> 开始生成 " + root.get(ProjectConstants.CLASS_NAME) +"Mapper.java 文件....... ");
        template = configuration.getTemplate(ProjectConstants.TEMPLATE_MAPPER);
        String mapperPath = PathUtils.packageNameToPath(root.get(ProjectConstants.BASE_PACKAGE_KEY) + ProjectConstants.MAPPER_PACKAGE_SUFFIX);
        File mapperDir = new File(ProjectConstants.PROJECT_PATH + ProjectConstants.JAVA_PATH + mapperPath);
        if(!mapperDir.exists()){
           mapperDir.delete();
            boolean mkdirs = mapperDir.mkdirs();
            if(mkdirs){
                logger.info("创建 mapper 目录成功");
            }
        }
        out = new FileWriter(new File(mapperDir, root.get(ProjectConstants.CLASS_NAME) + ProjectConstants.MAPPER_CLASS_SUFFIX));
        template.process(root, out);
        out.close();
        logger.info("========> " + root.get(ProjectConstants.CLASS_NAME) +"Mapper.java 文件生成完成.......");
    }




    public static void genServiceAndImpl(Map<String, Object> root) throws Exception{
        logger.info("========> 开始生成 " + root.get(ProjectConstants.CLASS_NAME) +"Service.java 文件....... ");
        Template template = configuration.getTemplate(ProjectConstants.TEMPLATE_SERVICE);
        String servicePath = PathUtils.packageNameToPath(root.get(ProjectConstants.BASE_PACKAGE_KEY)
                + ProjectConstants.SERVICE_PACKAGE_SUFFIX);
        File dir = new File(ProjectConstants.PROJECT_PATH + ProjectConstants.JAVA_PATH + servicePath);
        if(!dir.exists()){
            boolean isSuccess = dir.mkdirs();
            if(isSuccess){
                logger.info("目录不存在，创建目录成功");
            }
        }
        Writer out = new FileWriter(new File(dir, root.get(ProjectConstants.CLASS_NAME) + ProjectConstants.SERVICE_CLASS_SUFFIX));
        template.process(root, out);
        out.flush();
        logger.info("========>  " + root.get(ProjectConstants.CLASS_NAME) +"Service.java 文件生成完成.......");
        logger.info("========> 开始生成 " + root.get(ProjectConstants.CLASS_NAME) +"ServiceImpl.java 文件....... ");
        template = configuration.getTemplate(ProjectConstants.TEMPLATE_SERVICE_IMPL);
        String implPath = PathUtils.packageNameToPath(root.get(ProjectConstants.BASE_PACKAGE_KEY) + ProjectConstants.SERVICE_IMPL_PACKAGE_SUFFIX);
        dir = new File(ProjectConstants.PROJECT_PATH + ProjectConstants.JAVA_PATH + implPath);
        if(!dir.exists()){
            boolean isSuccess = dir.mkdirs();
            if(isSuccess){
                logger.info("目录不存在，创建目录成功");
            }
        }
        out = new FileWriter(new File(dir, root.get(ProjectConstants.CLASS_NAME) + ProjectConstants.SERVICE_IMPL_CLASS_SUFFIX));
        template.process(root, out);
        out.close();
        logger.info("========>  " + root.get(ProjectConstants.CLASS_NAME) +"ServiceImpl.java 文件生成完成.......");
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
            logger.info("========> isRest: " + isRest + "！=======>" +"生成带有 Restful 风格的 RestController 控制器.......");
            logger.info("========> 开始生成 " + root.get(ProjectConstants.CLASS_NAME) +"Controller.java 文件....... ");
            template = configuration.getTemplate(ProjectConstants.TEMPLATE_REST_CONTROLLER);
        }else{
            logger.info("========> isRest: " + isRest + "！=======>" +"生成不带 Restful 风格的 Controller 控制器.......");
            logger.info("========> 开始生成 " + root.get(ProjectConstants.CLASS_NAME) +"Controller.java 文件....... ");
            template = configuration.getTemplate(ProjectConstants.TEMPLATE_CONTROLLER);
        }
        String controllerPath = PathUtils.packageNameToPath(root.get(ProjectConstants.BASE_PACKAGE_KEY)
                + ProjectConstants.CONTROLLER_PACKAGE_SUFFIX);
        dir = new File(ProjectConstants.PROJECT_PATH + ProjectConstants.JAVA_PATH + controllerPath);
        if(!dir.exists()){
            boolean isSuccess = dir.mkdirs();
            if (isSuccess) {
                logger.info("目录不存在，创建目录成功");
            }
        }
        out = new FileWriter(new File(dir, root.get(ProjectConstants.CLASS_NAME)
                + ProjectConstants.CONTROLLER_CLASS_SUFFIX));
        template.process(root, out);
        out.close();
        logger.info("========>  " + root.get(ProjectConstants.CLASS_NAME) +"Controller.java 文件生成完成.......");
    }


    /**
     * 生成 mapper 映射文件
     * @param root
     * @throws Exception
     */
    public static void genMapperXml(Map<String, Object> root) throws Exception{
        logger.info("========> 开始生成 " + root.get(ProjectConstants.CLASS_NAME) +"Mapper.xml 文件....... ");
        Template template = configuration.getTemplate(ProjectConstants.TEMPLATE_MAPPER_XML);
        File dir = new File(ProjectConstants.PROJECT_PATH + ProjectConstants.RESOURCES_PATH +
                ProjectConstants.MAPPER_XML_DIR_SUFFIX);
        if(!dir.exists()){
            boolean mkdirs = dir.mkdirs();
            if(mkdirs){
                logger.info("mapper 目录不存在，创建 mapper 目录成功！");
            }
        }
        Writer out = new FileWriter(new File(dir, root.get(ProjectConstants.CLASS_NAME) +
                ProjectConstants.MAPPER_XML_FILE_SUFFIX));
        template.process(root, out);
        out.close();
        logger.info("========>  " + root.get(ProjectConstants.CLASS_NAME) +"Mapper.xml 文件生成完成.......");
    }


    /**
     * 生成分页实体类
     * @param root
     * @throws Exception
     */
    public static void genPageBean(Map<String, Object> root) throws Exception{
        Template template = configuration.getTemplate(ProjectConstants.TEMPLATE_PAGE_BEAN);
        String path =PathUtils.packageNameToPath(root.get(ProjectConstants.BASE_PACKAGE_KEY) +
                ProjectConstants.MODEL_PACKAGE_SUFFIX);
        File dir = new File(ProjectConstants.PROJECT_PATH + ProjectConstants.JAVA_PATH + path);
        if(!dir.exists()){
            boolean mkdirs = dir.mkdirs();
            if(mkdirs){
                logger.info("model  目录不存在，创建 model 目录成功！");
            }
        }
        Writer writer = new FileWriter(new File(dir, "PageBean" + ProjectConstants.MODEL_CLASS_SUFFIX));
        root.put(ProjectConstants.CLASS_NAME, "PageBean");
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
