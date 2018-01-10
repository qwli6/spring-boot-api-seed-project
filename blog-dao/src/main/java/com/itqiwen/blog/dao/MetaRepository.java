package com.itqiwen.blog.dao;

import com.itqiwen.blog.domain.Metas;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MetaRepository extends CrudRepository<Metas, Integer> {

    List<Metas> findMetasByType(String type);

}
