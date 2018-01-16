package com.itqiwen.blog.service.impl;

import com.itqiwen.blog.dao.LinkRepository;
import com.itqiwen.blog.domain.Link;
import com.itqiwen.blog.service.LinkService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class LinkServiceImpl implements LinkService {

    @Resource
    private LinkRepository linkRepository;

    @Override
    public void saveLink(Link link) {
        linkRepository.save(link);
    }

    @Override
    public Link findLinkById(Integer id) {
        return linkRepository.findOne(id);
    }

    @Override
    public List<Link> findAllLinks() {
        return linkRepository.findAll();
    }

    @Override
    public void deleteLink(Link link) {
        linkRepository.delete(link);
    }

    @Override
    public void deleteLink(Integer id) {
        linkRepository.delete(id);
    }

    @Override
    public void updateLink(Link link) {
//        linkRepository.saveAndFlush()
    }
}
