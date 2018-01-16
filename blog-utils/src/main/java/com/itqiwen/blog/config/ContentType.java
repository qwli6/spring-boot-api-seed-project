package com.itqiwen.blog.config;

/**
 * 内容类型
 * 私密 2
 * 可见 1
 * @author liqiwen
 */
public enum ContentType {

    PRIVATE("2"), PUBLIC("1");

    ContentType(String type) {
        this.type = type;
    }

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
