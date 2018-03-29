package com.company.project.configurer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonContainer;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.company.project.Application;
import com.company.project.core.ApplicationException;
import com.company.project.core.Result;
import com.company.project.core.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.util.List;

/**
 * 描述:
 * SpringMVC 配置
 * 统一异常处理
 *
 * @author liqiwen
 * @date 2018-03-29 22:24
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurationSupport {

    private Logger logger = LoggerFactory.getLogger(WebMvcConfigurer.class);

    //设置 FastJson 作为 Json 对象的转换器
    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();

        //设置转换 json 之后的属性
        //SerializerFeature.WriteMapNullValue 保留空的字段
        config.setSerializerFeatures(SerializerFeature.WriteMapNullValue,
                //Number 类型为 null 直接转换成 0
                SerializerFeature.WriteNullNumberAsZero,
                //字符串类型为 null 直接转换成 ""
                SerializerFeature.WriteNullStringAsEmpty,
                //禁止循环引用
                SerializerFeature.DisableCircularReferenceDetect);

        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(Charset.forName("UTF-8"));
        converters.add(converter);
    }


    /**
     * springmvc 路径匹配
     * @param configurer
     */
    @Override
    protected void configurePathMatch(PathMatchConfigurer configurer) {
        super.configurePathMatch(configurer);
    }

    @Override
    protected void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(new HandlerExceptionResolver() {
            @Nullable
            @Override
            public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                                 @Nullable Object handler, Exception exception) {
                Result result = new Result();
                if(exception instanceof ApplicationException){
                    //业务失败的异常，手动抛出的异常
                    result.setCode(ResultCode.FAILED).setMessage(exception.getMessage());
                    logger.info(exception.getMessage());
                }else if(exception instanceof NoHandlerFoundException){
                    //请求路径没有找到
                    result.setCode(ResultCode.NOT_FOUND).setMessage("接口 {" + request.getRequestURI() + "} 不存在，请检查");
                    logger.info(exception.getMessage());
                }else if(exception instanceof ServletException){
                    result.setCode(ResultCode.FAILED).setMessage(exception.getMessage());
                }else {
                    result.setCode(ResultCode.SERVER_ERROR)
                            .setMessage("服务器出错！接口 {" + request.getRequestURI() + "} 无法执行，请联系管理员！");
                    String message;
                    if (handler instanceof HandlerMethod) {
                        HandlerMethod handlerMethod = (HandlerMethod) handler;
                        message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常详细信息：%s",
                                request.getRequestURI(),
                                handlerMethod.getBean().getClass().getName(),
                                handlerMethod.getMethod().getName(),
                                exception.getMessage());
                    } else {
                        message = exception.getMessage();
                    }
                    logger.error(message, exception);
                }
                responseResult(response, result);
                return new ModelAndView();
            }
        });
    }


    /**
     * 静态资源处理器
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
    }

    /**
     * 添加自定义拦截器
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
    }

    /**
     * 跨域处理
     * @param registry
     */
    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        super.addCorsMappings(registry);
    }

    /**
     * 将返回的 json 数据通过 response.getWriter 写回
     * @param response
     * @param result
     */
    private void responseResult(HttpServletResponse response, Result result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try{
            response.getWriter().write(JSON.toJSONString(response));
        }catch (Exception exception){
            logger.error(exception.getMessage());
        }
    }
}
