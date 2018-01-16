package com.itqiwen.blog.config;

/**
 * 内容状态
 * 发布 1
 * 预览/草稿 2
 * 回收站 3
 * 永久删除 4
 * @author liqiwen
 */
public enum ContentState {

    PUBLISH("1"), DRAFT("2"), GARBAGE("3"), DELETE("4");

    ContentState(String state) {
        this.state = state;
    }

    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
