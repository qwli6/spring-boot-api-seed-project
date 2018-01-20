package com.itqiwen.blog.web.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

/**
 * 向 mvc 中添加自定义的组件
 */
@Component
public class WebMvcConfig extends WebMvcConfigurerAdapter{

    @Resource
    private BaseInterceptor interceptor;

    @Resource
    private AdminPostInterceptor adminPostInterceptor;

    /**
     * 把自己写的拦截器添加到 mvc 组件中去
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor);
        registry.addInterceptor(adminPostInterceptor);
    }

    /**
     * 静态资源文件的处理，使得外部可以直接访问，暂时没有用到
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
