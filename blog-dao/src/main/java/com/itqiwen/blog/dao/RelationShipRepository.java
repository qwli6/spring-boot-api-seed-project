package com.itqiwen.blog.dao;

import com.itqiwen.blog.domain.RelationShip;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationShipRepository extends CrudRepository<RelationShip, Integer> {

}
