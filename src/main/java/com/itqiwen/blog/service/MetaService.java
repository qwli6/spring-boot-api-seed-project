package com.itqiwen.blog.service;

import com.itqiwen.blog.entity.Meta;

import java.util.List;

public interface MetaService {

    void saveMeta(Meta meta);

    void updateMeta(Meta meta);

    void deleteMeta(Meta meta);

    List<Meta> findMetaByType(String type);
}
