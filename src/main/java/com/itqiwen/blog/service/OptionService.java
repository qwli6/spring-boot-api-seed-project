package com.itqiwen.blog.service;

import com.itqiwen.blog.entity.Options;

import java.util.List;

public interface OptionService {
    List<Options> findAllOption();

    void updateOptions(Options options);

    void saveOptions(Options options);

    Options findOptionsById(Long optionsId);

    void deleteOptions(Options options);
}
