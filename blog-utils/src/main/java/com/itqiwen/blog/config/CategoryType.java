package com.itqiwen.blog.config;

/**
 * 分类信息
 * @author liqiwen
 */
public enum CategoryType {

    INDEX("index"),
    ABOUT("about"),
    FRIENDS("friends"),
    SPRING_BOOT("spring-boot"),
    JAVA8("Java8"),
    BOOKS("books"),
    AD("ad"),
    JIWAI("jiwai"),
    ISSUE("issue");

    CategoryType(String type) {
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
