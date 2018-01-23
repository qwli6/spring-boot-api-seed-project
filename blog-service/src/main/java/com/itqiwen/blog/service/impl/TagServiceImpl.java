package com.itqiwen.blog.service.impl;

import com.itqiwen.blog.dao.TagRepository;
import com.itqiwen.blog.domain.Tag;
import com.itqiwen.blog.service.ITagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liqiwen
 */
@Service
@Transactional
public class TagServiceImpl implements ITagService {

    @Resource
    private TagRepository tagRepository;

    @Override
    public List<Tag> findTagList() {
        return tagRepository.findAll();
    }

    @Override
    public void saveTag(Tag tag) {
        tagRepository.save(tag);
    }

    @Override
    public Tag findTagByUrlName(String urlName) {
        return tagRepository.findTagByUrlName(urlName);
    }

    @Override
    public void updateTag(Tag tag) {
        tagRepository.saveAndFlush(tag);
    }
}
