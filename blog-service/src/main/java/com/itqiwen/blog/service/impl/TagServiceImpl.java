package com.itqiwen.blog.service.impl;

import com.itqiwen.blog.dao.TagRepository;
import com.itqiwen.blog.domain.Tag;
import com.itqiwen.blog.service.TagService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class TagServiceImpl implements TagService {

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
}
