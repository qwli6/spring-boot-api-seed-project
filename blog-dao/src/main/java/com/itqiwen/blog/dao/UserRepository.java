package com.itqiwen.blog.dao;

import com.itqiwen.blog.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String>{

//    User findUserByUsernameAndPassword(@Param("user") User user);

    User findUserByUsernameAndPassword(
            @Param("username")String username,
            @Param("password")String password);

    User findUserByUsername(@Param("username")String username);

}
