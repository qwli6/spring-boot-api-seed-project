package com.itqiwen.blog.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = TipException.class)
    public String customException(Exception e){
        LOGGER.error("the simple_blog application has occurred 500 exception:e={}", e.getMessage());
        e.printStackTrace();
        return "common/error_500";
    }

    @ExceptionHandler(value = Exception.class)
    public String exception(Exception e){
        LOGGER.error("the simple_blog application has occurred 404 exception:e={}", e.getMessage());
        e.printStackTrace();
        return "common/error_500";
    }
}
