package com.itqiwen.blog.service;

import com.itqiwen.blog.domain.BlogSystem;
import com.itqiwen.blog.exception.TipException;

import java.util.List;

public interface SystemService {


    Boolean checkLogin(String username, String password) throws TipException;

    Boolean findUserByName(String username) throws TipException;
}
