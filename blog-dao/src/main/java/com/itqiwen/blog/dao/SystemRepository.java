package com.itqiwen.blog.dao;

import com.itqiwen.blog.domain.BlogSystem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<BlogSystem, String>{

//    BlogSystem findUserByUsernameAndPassword(@Param("user") BlogSystem user);

    BlogSystem findUserByUsernameAndPassword(
            @Param("username")String username,
            @Param("password")String password);

    BlogSystem findUserByUsername(@Param("username")String username);

}
