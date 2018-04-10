package com.company.project.configurer;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 描述:
 *  druid 连接池配置信息
 *  这里的连接池相关的配置全部都是使用 druid springboot 整合官方示例中的配置，并不一定保证适合生产环境
 *  如果有需要，可以自己自行对配置进行更改。
 *  如何更改？
 *  所有通用的配置都在 application.properties 中
 * @author liqiwen
 * @date 2018-04-10 09:33
 */
@Configuration
public class DruidConfigurer {
    private Logger logger = LoggerFactory.getLogger(DruidConfigurer.class);
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.druid.initial-size}")
    private Integer initialSize;//2
    @Value("${spring.datasource.druid.max-active}")
    private Integer maxActive;//30
    @Value("${spring.datasource.druid.min-idle}")
    private Integer minIdle;//2
    @Value("${spring.datasource.druid.max-wait}")
    private Long maxWait;//1234
    @Value("${spring.datasource.druid.pool-prepared-statements}")
    private Boolean poolPreparedStatements;//true
    @Value("${spring.datasource.druid.max-pool-prepared-statement-per-connection-size}")
    private Integer maxPoolPreparedStatementPerConnectionSize ;//5
    @Value("${spring.datasource.druid.validation-query}")
    private String validationQuery;//1
    @Value("${spring.datasource.druid.validation-query-timeout}")
    private Integer validationQueryTimeout;//1
    @Value("${spring.datasource.druid.test-on-borrow}")
    private Boolean testOnBorrow;//true
    @Value("${spring.datasource.druid.test-on-return}")
    private Boolean testOnReturn;//true
    @Value("${spring.datasource.druid.test-while-idle}")
    private Boolean testWhileIdle;//true
    @Value("${spring.datasource.druid.time-between-eviction-runs-millis}")
    private Integer timeBetweenEvictionRunsMillis;//10000
    @Value("${spring.datasource.druid.min-evictable-idle-time-millis}")
    private Integer minEvictableIdleTimeMillis;//30001
    @Value("${spring.datasource.druid.async-close-connection-enable}")
    private Boolean asyncCloseConnectionEnable;//true

    @Bean
    @Primary
    public DataSource dataSource(){
        logger.info("DruidConfigurer ==> starting init druid datasource.....");
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setInitialSize(initialSize);
        dataSource.setMaxActive(maxActive);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxWait(maxWait);
        dataSource.setPoolPreparedStatements(poolPreparedStatements);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        dataSource.setValidationQuery(validationQuery);
        dataSource.setValidationQueryTimeout(validationQueryTimeout);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dataSource.setAsyncCloseConnectionEnable(asyncCloseConnectionEnable);
        logger.info("DruidConfigurer ==> starting init druid datasource completed.....");
        return dataSource;
    }
}
