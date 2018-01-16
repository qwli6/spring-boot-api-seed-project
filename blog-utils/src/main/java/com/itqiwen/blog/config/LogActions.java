package com.itqiwen.blog.config;

/**
 * 日志相关动作
 * 登录后台
 * 修改密码
 * 修改个人信息
 * 删除文章
 * 新增文章
 * 修改文章
 * 删除分类
 * 新增分类
 * 修改分类
 * 删除标签
 * 新增表桥
 * 修改标签

 * @author liqiwen
 */
public enum LogActions {

    LOGIN("登录后台"), UP_PWD("修改密码"), UP_INFO("修改个人信息"),
    ADD_ARTICLE("新增文章"), DEL_ARTICLE("删除文章"),UP_ARTICLE("更新文章"),
    ADD_CATEGORY("新增分类"), DEL_CATEGORY("删除分类"), UP_CATEGORY("更新分类"),
    ADD_TAG("新增标签"), DEL_TAG("删除标签"), UP_TAG("更新标签"),
    DEL_PAGE("删除页面"), SYS_BACKUP("系统备份"),
    SYS_SETTING("保存系统设置"), INIT_SITE("初始化站点"),LOGOUT("退出登录");

    private String action;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    LogActions(String action) {
        this.action = action;
    }
}