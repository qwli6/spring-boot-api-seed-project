package com.itqiwen.blog.service;

import com.itqiwen.blog.domain.Menu;

import java.util.List;

/**
 * @author liqiwen
 */
public interface IMenuService {


    /**
     * 查找全部菜单
     * @return 菜单/分类列表
     */
    List<Menu> findMenuList();

    /**
     * 根据 url 查找菜单
     * @param url 菜单 url
     * @return 菜单实体类
     */
    Menu findMenuByUrl(String url);
}
