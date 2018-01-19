package com.itqiwen.blog.service.impl;

import com.itqiwen.blog.dao.CategoryRepository;
import com.itqiwen.blog.domain.Menu;
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
    public List<Menu> findAllCategory() {
        return (List<Menu>) categoryRepository.findAll();
    }

    @Override
    public Menu findCategoryByUrl(String url) {
        return categoryRepository.findCategoryByUrl(url);
    }
}
