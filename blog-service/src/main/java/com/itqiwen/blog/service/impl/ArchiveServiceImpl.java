package com.itqiwen.blog.service.impl;

import com.itqiwen.blog.dao.ArchiveRepository;
import com.itqiwen.blog.domain.Archive;
import com.itqiwen.blog.service.IArchiveService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liqiwen
 */
@Transactional
@Service
public class ArchiveServiceImpl implements IArchiveService {
    @Resource
    private ArchiveRepository archiveDao;

    @Override
    public List<Archive> findArchiveList() {
        Sort sort = new Sort(Sort.Direction.DESC, "archiveId");
        return archiveDao.findAll(sort);
    }

    @Override
    public Archive findArchiveByTitle(String title) {
        return archiveDao.findArchiveByTitle(title);
    }

    @Override
    public Archive saveArchive(Archive newArchive) {
        return archiveDao.save(newArchive);
    }

    @Override
    public void updateCount(Archive archive) {
        archiveDao.saveAndFlush(archive);
    }

    @Override
    public Archive findArchiveByUrlName(String urlName) {
        return null;
    }
}
