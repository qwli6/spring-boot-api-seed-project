package com.itqiwen.blog.dao;

import com.itqiwen.blog.domain.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRespository extends CrudRepository<UserInfo, Integer> {

}
