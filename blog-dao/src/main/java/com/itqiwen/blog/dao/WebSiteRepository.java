package com.itqiwen.blog.dao;

import com.itqiwen.blog.domain.Options;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebSiteRepository extends CrudRepository<Options, Integer> {
}
