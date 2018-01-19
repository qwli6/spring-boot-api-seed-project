package com.itqiwen.blog.service.impl;

import com.itqiwen.blog.dao.ContentRepository;
import com.itqiwen.blog.domain.Menu;
import com.itqiwen.blog.domain.Content;
import com.itqiwen.blog.service.ContentService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service
public class ContentServiceImpl implements ContentService {

    @Resource
    private ContentRepository contentRepository;


    /**
     * 查询全部分类下的文章
     * 带分页
     * @param pageCode
     * @param pageSize
     * @return
     */
    @Override
    public Page<Content> findContentByCriteria(Integer pageCode, Integer pageSize) {
        //hibernate 页码是从 0开始，这里需要 -1
        Pageable pageable =new PageRequest(pageCode-1, pageSize, Sort.Direction.DESC, "createDt");
        Content content = new Content();
//        content.setState(ContentState.PUBLISH.getState());
//        content.setType(ContentType.PUBLIC.getType());
        Example<Content> contentExample = Example.of(content);
        return contentRepository.findAll(contentExample, pageable);
    }

    /**
     * 根据分类查询条件
     * 带分页
     * @param pageCode  //当前页码
     * @param pageSize  //当前页码大小
     * @param category  //当前分类
     * @return
     */
    @Override
    public Page<Content> findContentByCriteria(Integer pageCode, Integer pageSize, Menu category) {
        Pageable pageable = new PageRequest(pageCode - 1, pageSize, Sort.Direction.DESC, "createDt");
        Content content = new Content();
//        content.setState(ContentState.PUBLISH.getState());
//        content.setType(ContentType.PUBLIC.getType());
        Example<Content>  contentExample = Example.of(content);
        return contentRepository.findAll(contentExample, pageable);
    }


    @Override
    public List<Content> findContentByCriteria(Menu category) {
        Content content = new Content();
//        content.setState(ContentState.PUBLISH.getState());
//        content.setType(ContentType.PUBLIC.getType());
//        content.setCategory(category);
        Example<Content> contentExample = Example.of(content);
        Sort sort = new Sort(Sort.Direction.DESC, "createDt");
        return contentRepository.findAll(contentExample, sort);
    }

    @Override
    public void saveContent(Content content) {
        contentRepository.save(content);
    }

    @Override
    public Content findContentById(String cid) {
        return contentRepository.findOne(Integer.parseInt(cid));
    }

    @Override
    public Content findContentByVisitUrl(String url) {
//        return contentRepository.findContentByVisitUrl(url);
        return null;
    }

    @Override
    public void updateContent(Content content) {
        contentRepository.saveAndFlush(content);
    }

    @Override
    public List<Content> findContentsByCategory(Menu category) {
//        return contentRepository.findContentsByCategory(category);
        return null;
    }

    @Override
    public void deleteContent(Content content) {
        contentRepository.delete(content);
    }

    @Override
    public void updateVisitCount(Integer cid, int visitCount) {
        contentRepository.updateVisitCount(cid, visitCount);
    }


}
