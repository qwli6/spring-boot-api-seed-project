package com.itqiwen.blog.entity;

import java.util.List;

/**
 * 基础响应信息
 * @author selfassu
 * @param <T>
 */
public class RestResponse<T> {

    private String msg; //响应消息
    private List<T> data; //响应数据
    private String code; //响应码


    public RestResponse() {
    }


    public RestResponse(String msg, List<T> data, String code){
        this.msg = msg;
        this.data = data;
        this.code = code;
    }

    public RestResponse(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
