package com.company.project.core;

import com.alibaba.fastjson.JSON;

/**
 * 描述:
 * 统一 API 处理结果
 *
 * @author liqiwen
 */
public class Result<T> {

    private int code; //返回码
    private String message; //返回消息
    private T data; //返回数据

    public Result setCode(ResultCode resultCode){
        this.code = resultCode.code();
        return this;
    }

    public int getCode(){
        return code;
    }

    public String getMessage(){
        return message;
    }

    public Result setMessage(String message){
        this.message = message;
        return this;
    }

    public T getData(){
        return data;
    }

    public Result setData(T data){
        this.data = data;
        return this;
    }

    public String toString(){
        return JSON.toJSONString(this);
    }
}
