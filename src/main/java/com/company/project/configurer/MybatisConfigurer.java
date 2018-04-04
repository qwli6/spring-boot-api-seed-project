package com.company.project.configurer;

import com.company.project.core.ProjectConstants;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.generator.config.*;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * 描述:
 *
 * @author liqiwen
 * @date 2018-04-04 09:02
 */
@Configuration
public class MybatisConfigurer {

    @Bean(name = "sqlSessionFactoryBean")
    public SqlSessionFactory sqlSessionFactoryBean(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/*Mapper.xml"));
        //给 model 设置别名
        sqlSessionFactoryBean.setTypeAliasesPackage(ProjectConstants.BASE_TARGET_PACKAGE + ProjectConstants.MODEL_PACKAGE_SUFFIX);
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("pageSizeZero", "true");
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        pageHelper.setProperties(properties);
        //设置分页插件 TODO 这里有问题
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{(Interceptor) pageHelper});
        return sqlSessionFactoryBean.getObject();
    }


    /**
     * 配置 mybatis mapper 接口扫描配置
     * @return mapperScannerConfigurer
     * import org.mybatis.spring.mapper.MapperScannerConfigurer;
     * import tk.mybatis.spring.mapper.MapperScannerConfigurer;
     * 注意这里有两个包，如果想用通用 mapper 需要使用下面的这个包
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
        //扫描哪一个包
        mapperScannerConfigurer.setBasePackage(ProjectConstants.BASE_TARGET_PACKAGE + ProjectConstants.MAPPER_PACKAGE_SUFFIX);
        //配置通用的 mapper，记住，通用 mapper 不能被扫描到。
        //可以在配置文件中进行配置。
        Properties properties = new Properties();
        //通用 mapper 的路径
        properties.setProperty("mappers", ProjectConstants.COMMON_MAPPER_LOCATION);
        properties.setProperty("notEmpty", "false");
        properties.setProperty("IDENTITY", "MYSQL");
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }
}
