package com.company.project.core;

/**
 * 描述:
 * 异常处理器
 *
 * @author liqiwen
 * @date 2018-03-29 22:52
 */
public class ApplicationException extends RuntimeException{
    private int code;
    private String msg;

    public ApplicationException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ApplicationException(String message, int code, String msg) {
        super(message);
        this.code = code;
        this.msg = msg;
    }

    public ApplicationException(String message, Throwable cause, int code, String msg) {
        super(message, cause);
        this.code = code;
        this.msg = msg;
    }

    public ApplicationException(Throwable cause, int code, String msg) {
        super(cause);
        this.code = code;
        this.msg = msg;
    }

    public ApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code, String msg) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
