package com.itqiwen.blog.domain;


/**
 * 基础响应信息
 * @author selfassu@gmail.com
 * @param <T>
 */
public class RestResult<T> {

    /**
     * 服务器响应数据
     */
    private T payload;

    /**
     * 请求是否成功
     */
    private boolean success;

    /**
     * 错误信息
     */
    private String msg;

    /**
     * 状态码
     */
    private int code = -1;

    /**
     * 服务器响应时间
     */
    private long timestamp;

    public RestResult() {
        this.timestamp = System.currentTimeMillis() / 1000;
    }

    public RestResult(boolean success) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
    }

    public RestResult(boolean success, T payload) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
        this.payload = payload;
    }

    public RestResult(boolean success, T payload, int code) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
        this.payload = payload;
        this.code = code;
    }

    public RestResult(boolean success, String msg) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
        this.msg = msg;
    }

    public RestResult(boolean success, String msg, int code) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
        this.msg = msg;
        this.code = code;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public static RestResult ok() {
        return new RestResult(true);
    }

    public static <T> RestResult ok(T payload) {
        return new RestResult(true, payload);
    }

    public static <T> RestResult ok(int code) {
        return new RestResult(true, null, code);
    }

    public static <T> RestResult ok(T payload, int code) {
        return new RestResult(true, payload, code);
    }

    public static RestResult fail() {
        return new RestResult(false);
    }

    public static RestResult fail(String msg) {
        return new RestResult(false, msg);
    }

    public static RestResult fail(int code) {
        return new RestResult(false, null, code);
    }

    public static RestResult fail(int code, String msg) {
        return new RestResult(false, msg, code);
    }

}
