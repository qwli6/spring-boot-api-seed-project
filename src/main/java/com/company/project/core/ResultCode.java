package com.company.project.core;

/**
 * 描述:
 * 响应码枚举
 *
 * @author liqiwen
 */
public enum ResultCode {
    SUCCESS(200),
    FAILED(400),
    UNAUTHORIZED(401),
    NOT_FOUND(404),
    SERVER_ERROR(500);

    private final int code;

    ResultCode(int code){
        this.code = code;
    }

    public int code(){
        return code;
    }
}
