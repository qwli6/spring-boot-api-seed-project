package com.itqiwen.blog.service;

import com.itqiwen.blog.domain.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAllCategory();

    Category findCategoryByUrl(String url);
}
