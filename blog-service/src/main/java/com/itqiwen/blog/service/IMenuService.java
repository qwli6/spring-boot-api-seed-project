package com.itqiwen.blog.service;

import com.itqiwen.blog.domain.Menu;

import java.util.List;

public interface MenuService {

    List<Menu> findAllCategory();

    Menu findCategoryByUrl(String url);
}
