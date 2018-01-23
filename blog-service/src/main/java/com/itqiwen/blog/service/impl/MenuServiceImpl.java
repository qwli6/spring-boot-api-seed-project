package com.itqiwen.blog.service.impl;

import com.itqiwen.blog.dao.MenuRepository;
import com.itqiwen.blog.domain.Menu;
import com.itqiwen.blog.service.IMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liqiwen
 */
@Service
@Transactional
public class MenuServiceImpl implements IMenuService {

    @Resource
    private MenuRepository menuDao;



    @Override
    public List<Menu> findMenuList() {
        return (List<Menu>) menuDao.findAll();
    }

    @Override
    public Menu findMenuByUrl(String url) {
        return menuDao.findMenuByUrl(url);
    }
}
