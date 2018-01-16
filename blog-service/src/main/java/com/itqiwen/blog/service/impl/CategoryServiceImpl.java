package com.itqiwen.blog.service.impl;

import com.itqiwen.blog.dao.CategoryRepository;
import com.itqiwen.blog.domain.Category;
import com.itqiwen.blog.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAllCategory() {
        return (List<Category>) categoryRepository.findAll();
    }

    @Override
    public Category findCategoryByUrl(String url) {
        return categoryRepository.findCategoryByUrl(url);
    }
}
